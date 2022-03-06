package com.ldh.modules.order.service.impl;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.inventoryService.model.MerchantModel;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.mapper.AfterSalesMapper;
import com.ldh.modules.order.model.AfterSalesDetailsModel;
import com.ldh.modules.order.model.AfterSalesModel;
import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import constant.AfterSalesDisposeEnum;
import constant.UploadFileConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.beancontext.BeanContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Description: 售后
 * @Author: jeecg-boot
 * @Date:   2022-02-26
 * @Version: V1.0
 */
@Service
public class AfterSalesServiceImpl extends ServiceImpl<AfterSalesMapper, AfterSales> implements AfterSalesService {

    @Autowired
    private OrderInformationService orderInformationService;

    @Autowired
    private AfterSalesMapper afterSalesMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private AuthorityClient authorityClient;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Override
    @Transactional
    public void applyAfterSales(AfterSales afterSales) {

        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);

        //更改订单售后状态
        OrderInformation orderInformation = new OrderInformation();
        orderInformation
                .setOrderId(afterSales.getOrderId())
                .setIsAfterSales("1");
        orderInformationService.updateById(orderInformation);

        //创建售后单
        OrderInformation orderInformationTemp = orderInformationService.getById(afterSales.getOrderId());
        afterSales
                .setMerchantId(orderInformationTemp.getMerchantId())
                .setInventoryId(orderInformationTemp.getInventoryId())
                .setCreateBy(authorityInformation.getAuthorityId())
                .setSts("ing")
                .setAfterSalesSts(AfterSalesDisposeEnum.wait_merchant.getSts());
        this.save(afterSales);
    }

    @Override
    public Page<AfterSalesModel> list(Page page, QueryWrapper queryWrapper, AfterSales afterSales) {

        Page<AfterSalesModel> iPage = afterSalesMapper.list(page, queryWrapper,afterSales);
        List<AfterSalesModel> list = iPage.getRecords();
        if (list.isEmpty()){
            return iPage;
        }

        //请求体
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder merchantIds = new StringBuilder();
        List<String> inventoryIds = new LinkedList<>();
        list.stream().forEach(e->{
            inventoryIds.add(e.getInventoryId());
            stringBuilder.append(e.getInventoryId());
            stringBuilder.append(",");
            merchantIds.append(e.getMerchantId());
            merchantIds.append(",");
        });
        ImageListGetVO imageListGetVO = new ImageListGetVO();
        imageListGetVO
                .setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                .setObjectId(inventoryIds);


        //请求
        Result<List<ImageNoteModel>> imageResult = imageNoteGetClient.getByObjectIdAndImgGroupList(imageListGetVO);
        Result<List<Inventory>> inventoryResult = inventoryClient.selectByIds(stringBuilder.toString());
        Result<List<MerchantModel>> merchantResult = merchantClient.selectByIds(merchantIds.toString());


        if (!inventoryResult.isSuccess() && !imageResult.isSuccess() && !merchantResult.isSuccess()){
            throw new RuntimeException("Fegin error");
        }else {
            List<Inventory> inventoryList = inventoryResult.getResult();
            List<MerchantModel> merchantModelList = merchantResult.getResult();
            Map<String, Inventory> inventoryMap = inventoryList.stream().collect(Collectors.toMap(Inventory::getId, r->r));
            Map<String, MerchantModel> merchantModelMap = merchantModelList.stream().collect(Collectors.toMap(MerchantModel::getMerchantId, r->r));

            list.forEach(e->{
                if (inventoryMap.containsKey(e.getInventoryId())){
                    e.setInventoryName(inventoryMap.get(e.getInventoryId()).getInventoryName());
                }
                if (merchantModelMap.containsKey(e.getMerchantId())){
                    e.setMerchantName(merchantModelMap.get(e.getMerchantId()).getMerchantName());
                }

            });


            List<ImageNoteModel> imageNoteModelList = imageResult.getResult();
            Map<String, List<ImageNoteModel>> imageNoteModelMap = new HashMap<>();
            imageNoteModelList.stream().forEach(e->{
                if (!imageNoteModelMap.containsKey(e.getObjectId())){
                    List<ImageNoteModel> list1 = new LinkedList<>();
                    list1.add(e);
                    imageNoteModelMap.put(e.getObjectId(),list1);
                }else {
                    imageNoteModelMap.get(e.getObjectId()).add(e);
                }
            });
            //将排序值最小的图片加入到list的firstimg中
            list.stream().forEach(e->{
                List<ImageNoteModel> modelList =  imageNoteModelMap.get(e.getInventoryId());
                if (modelList != null){
                    int min = 0;
                    int current = 0;
                    for (ImageNoteModel model : modelList){
                        if (model.getSort() < min){
                            current = min;
                        }
                        current++;
                    }
                    e.setFirstImage(modelList.get(min).getImgPath());
                }
            });
        }

        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public Page<AfterSalesModel> listMerchant(Page page, QueryWrapper queryWrapper, AfterSales afterSales) {
        Page<AfterSalesModel> iPage = afterSalesMapper.list(page, queryWrapper,afterSales);
        List<AfterSalesModel> list = iPage.getRecords();
        if (list.isEmpty()){
            return iPage;
        }
        //请求体
        StringBuilder stringBuilder = new StringBuilder();
        list.stream().forEach(e->{
            stringBuilder.append(e.getInventoryId());
            stringBuilder.append(",");

        });
        //请求
        Result<List<Inventory>> inventoryResult = inventoryClient.selectByIds(stringBuilder.toString());

        if (!inventoryResult.isSuccess()){
            throw new RuntimeException("Fegin error");
        }else {
            List<Inventory> inventoryList = inventoryResult.getResult();
            Map<String, Inventory> inventoryMap = inventoryList.stream().collect(Collectors.toMap(Inventory::getId, r -> r));
            list.forEach(e->{
                if (inventoryMap.containsKey(e.getInventoryId())){
                    e.setInventoryName(inventoryMap.get(e.getInventoryId()).getInventoryName());
                }
            });
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public AfterSalesDetailsModel getAfterSalesDetail(String afterSalesId) throws Exception {
        AfterSales afterSales = this.getById(afterSalesId);

        ImageGetVO imageGetVO = new ImageGetVO();
        imageGetVO
                .setImgGroup(UploadFileConstant.AFTER_SALES_IMAGE)
                .setObjectId(afterSalesId);

        Result<Inventory> inventoryResult = inventoryClient.getById(afterSales.getInventoryId());
        Result<AuthorityInformationModel> authorityInformationResult = authorityClient.selectById(afterSales.getCreateBy());
        Result<List<ImageNoteModel>> imageResult = imageNoteGetClient.getByObjectIdAndImgGroup(imageGetVO);

        if (!inventoryResult.isSuccess() && !authorityInformationResult.isSuccess() && !imageResult.isSuccess()){
            throw new Exception("fegin error");
        }

        Inventory tempInventory = inventoryResult.getResult();
        AuthorityInformationModel tempAuthorityInformationModel = authorityInformationResult.getResult();
        List<ImageNoteModel> tempImageNoteModelList = imageResult.getResult();
        List<String> imageSrcList = tempImageNoteModelList.stream().map(e -> e.getImgPath()).collect(Collectors.toList());

        AfterSalesDetailsModel afterSalesDetailsModel = new AfterSalesDetailsModel();
        BeanUtils.copyProperties(afterSales, afterSalesDetailsModel);
        afterSalesDetailsModel
                .setInventoryCode(tempInventory.getInventoryCode())
                .setInventoryName(tempInventory.getInventoryName())

                .setUserName(tempAuthorityInformationModel.getAuthorityUsername())
                .setUserAuthorityName(tempAuthorityInformationModel.getAuthorityName())
                .setUserPhone(tempAuthorityInformationModel.getPhone())
                .setReasonImageList(imageSrcList);

        return afterSalesDetailsModel;
    }
}
