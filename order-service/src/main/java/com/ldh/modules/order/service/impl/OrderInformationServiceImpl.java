package com.ldh.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.mapper.OrderInformationMapper;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderMerchantInformationModel;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.userService.client.AuthorityAddressClient;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityAddressModel;
import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import constant.UploadFileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Service
public class OrderInformationServiceImpl extends ServiceImpl<OrderInformationMapper, OrderInformation> implements OrderInformationService {

    @Autowired
    private OrderInformationMapper orderInformationMapper;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private AuthorityAddressClient authorityAddressClient;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Override
    public Page<OrderInformationModel> list(Page page, QueryWrapper queryWrapper, OrderInformation orderInformation) {
        return orderInformationMapper.list(page, queryWrapper, orderInformation);
    }

    @Override
    public Page<OrderInformationModel> listToBuyer(Page page, QueryWrapper queryWrapper, OrderInformation orderInformation) {

        Page<OrderInformationModel> page1 = orderInformationMapper.list(page, queryWrapper, orderInformation);
        List<OrderInformationModel> orderInformationModels = page1.getRecords();


        //请求体
        StringBuilder stringBuilder = new StringBuilder();
        List<String> orderIds = new LinkedList<>();
        orderInformationModels.stream().forEach(e->{
            orderIds.add(e.getInventoryId());
            stringBuilder.append(e.getInventoryId());
            stringBuilder.append(",");
        });
        ImageListGetVO imageListGetVO = new ImageListGetVO();
        imageListGetVO
                .setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                .setObjectId(orderIds);


        //请求
        Result<List<ImageNoteModel>> imageResult = imageNoteGetClient.getByObjectIdAndImgGroupList(imageListGetVO);
        Result<List<Inventory>> inventoryResult = inventoryClient.selectByIds(stringBuilder.toString());


        if (!inventoryResult.isSuccess() && !imageResult.isSuccess()){
            throw new RuntimeException("Fegin error");
        }else {
            List<Inventory> inventoryList = inventoryResult.getResult();
            List<ImageNoteModel> imageNoteModelList = imageResult.getResult();

            Map<String, Inventory> map = inventoryList.stream().collect(Collectors.toMap(Inventory::getId, r->r));

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
            orderInformationModels.stream().forEach(e->{
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
                    e.setFirstImagePath(modelList.get(min).getImgPath());
                }
            });
            orderInformationModels.stream().forEach(e->{
                if (map.containsKey(e.getInventoryId())){
                    e.setInventoryName(map.get(e.getInventoryId()).getInventoryName());
                }
            });
        }



        page1.setRecords(orderInformationModels);
        return page1;
    }

    @Override
    public Page<OrderInformationModel> listToMerchant(Page page, QueryWrapper queryWrapper, OrderInformation orderInformation) throws Exception {
        Page<OrderInformationModel> page1 = orderInformationMapper.list(page, queryWrapper, orderInformation);
        List<OrderInformationModel> list = page1.getRecords();
        StringBuilder stringBuilder = new StringBuilder();
        list.stream().forEach(e->{
            stringBuilder.append(e.getInventoryId());
            stringBuilder.append(",");
        });
        Result<List<Inventory>> result = inventoryClient.selectByIds(stringBuilder.toString());
        if (!result.isSuccess()){
            throw new RuntimeException("Fegin error");
        }else {
            List<Inventory> inventoryList = result.getResult();
            Map<String, Inventory> map = inventoryList.stream().collect(Collectors.toMap(Inventory::getId, r->r));
            list.stream().forEach(e->{
                if (map.containsKey(e.getInventoryId())){
                    e.setInventoryName(map.get(e.getInventoryId()).getInventoryName());
                }
            });
            page1.setRecords(list);
        }
        return page1;
    }

    @Override
    public OrderMerchantInformationModel getByIdForMerchantDetail(String id) {
        OrderInformation orderInformation = this.getById(id);
        if (orderInformation == null)
            throw new RuntimeException("orderInformation is null");

        Result<AuthorityAddressModel> result = authorityAddressClient.queryById(orderInformation.getAddressId());
        Result<Inventory> result1 = inventoryClient.getById(orderInformation.getInventoryId());

        if (!result.isSuccess() && !result1.isSuccess())
            throw new RuntimeException("fegin error");

        AuthorityAddressModel authorityAddressModel = result.getResult();
        Inventory inventory = result1.getResult();

        OrderMerchantInformationModel orderMerchantInformationModel = new OrderMerchantInformationModel(orderInformation);
        orderMerchantInformationModel
                .setAddressName(authorityAddressModel.getAddressName())
                .setAddressPhone(authorityAddressModel.getAddressPhone())
                .setAddressSite(authorityAddressModel.getAddressSite())
                .setInventoryName(inventory.getInventoryName());
        return orderMerchantInformationModel;
    }
}
