package com.ldh.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.inventoryService.model.InventoryModel;
import com.ldh.inventoryService.model.MerchantModel;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.service.IPhysicalDistributionService;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.mapper.OrderInformationMapper;
import com.ldh.modules.order.model.OrderInformationDetailModel;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderMerchantInformationModel;
import com.ldh.modules.order.model.PhysicalDetailModel;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.modules.order.vo.SendInventoryVO;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.userService.client.AuthorityAddressClient;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityAddressModel;
import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import constant.UploadFileConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 订单信息表
 * @Author: ldh
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
    private AuthorityClient authorityClient;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Autowired
    private OrderPhysicalDistributionService orderPhysicalDistributionService;

    @Autowired
    private IPhysicalDistributionService physicalDistributionService;

    @Override
    public Page<OrderInformationModel> list(Page page, QueryWrapper queryWrapper, OrderInformation orderInformation) throws Exception {
        Page<OrderInformationModel> page1 = orderInformationMapper.list(page, queryWrapper, orderInformation);
        List<OrderInformationModel> orderInformationModelList = page1.getRecords();
        //构建请求参数
        StringBuilder inventoryIds = new StringBuilder();
        StringBuilder merchantIds = new StringBuilder();
        StringBuilder userIds = new StringBuilder();
        //去重
        Set<String> inventoryIdSet = new HashSet<>();
        Set<String> merchantIdSet = new HashSet<>();
        Set<String> userIdSet = new HashSet<>();
        orderInformationModelList.stream().forEach(e->{
            inventoryIdSet.add(e.getInventoryId());
            merchantIdSet.add(e.getMerchantId());
            userIdSet.add(e.getCreateBy());
        });
        inventoryIdSet.forEach(e->{
            inventoryIds.append(e);
            inventoryIds.append(",");
        });
        merchantIdSet.forEach(e->{
            merchantIds.append(e);
            merchantIds.append(",");
        });
        userIdSet.forEach(e->{
            userIds.append(e);
            userIds.append(",");
        });
        //发送请求
        Result<List<Inventory>> inventoryResult = inventoryClient.selectByIds(inventoryIds.toString());
        Result<List<MerchantModel>> merchantResult = merchantClient.selectByIds(merchantIds.toString());
        Result<List<AuthorityInformationModel>> userResult = authorityClient.selectByIds(userIds.toString());

        if (inventoryResult.isSuccess() && merchantResult.isSuccess() && userResult.isSuccess()){
            Map<String, Inventory> inventoryMap = inventoryResult.getResult().stream().collect(Collectors.toMap(Inventory::getId,r->r));
            Map<String, MerchantModel> merchantModelMap = merchantResult.getResult().stream().collect(Collectors.toMap(MerchantModel::getMerchantId,r->r));
            Map<String, AuthorityInformationModel> authorityInformationModelMap = userResult.getResult().stream().collect(Collectors.toMap(AuthorityInformationModel::getAuthorityId, r->r));
            orderInformationModelList.stream().forEach(e->{
                if (inventoryMap.containsKey(e.getInventoryId())){
                    e.setInventoryCode(inventoryMap.get(e.getInventoryId()).getInventoryCode());
                }
                if (merchantModelMap.containsKey(e.getMerchantId())){
                    e.setMerchantCode(merchantModelMap.get(e.getMerchantId()).getMerchantCode());
                }
                if (authorityInformationModelMap.containsKey(e.getCreateBy())){
                    e.setUserName(authorityInformationModelMap.get(e.getCreateBy()).getAuthorityUsername());
                }
            });
            page1.setRecords(orderInformationModelList);
        }else {
            throw new Exception("fegin error");
        }
        return page1;
    }

    @Override
    public Page<OrderInformationModel> listToBuyer(Page page, QueryWrapper queryWrapper, OrderInformation orderInformation) {

        Page<OrderInformationModel> page1 = orderInformationMapper.list(page, queryWrapper, orderInformation);
        List<OrderInformationModel> orderInformationModels = page1.getRecords();
        if (orderInformationModels.isEmpty()){
            return page1;
        }

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

    @Override
    public OrderInformationDetailModel getByIdDetail(String id) throws Exception {
        OrderInformation orderInformation = this.getById(id);
        Result<Inventory> inventoryResult = inventoryClient.getById(orderInformation.getInventoryId());
        Result<MerchantModel> merchantResult = merchantClient.selectById(orderInformation.getMerchantId());
        Result<AuthorityInformationModel> userResult = authorityClient.selectById(orderInformation.getCreateBy());

        MerchantModel merchantModel = merchantResult.getResult();
        Inventory inventory = inventoryResult.getResult();
        AuthorityInformationModel authorityInformationModel = userResult.getResult();

        OrderInformationDetailModel orderInformationDetailModel = null;
        if (inventoryResult.isSuccess() && merchantResult.isSuccess() && userResult.isSuccess()){
            //订单基础信息
            orderInformationDetailModel = new OrderInformationDetailModel(orderInformation);

            //商家详情
            orderInformationDetailModel
                    .setMerchantCode(merchantModel.getMerchantCode())
                    .setMerchantInformation(merchantModel.getMerchantInformation())
                    .setMerchantName(merchantModel.getMerchantName())
                    .setMerchantSts(merchantModel.getSts())
                    .setRecordIdentityCode(merchantModel.getRecordIdentityCode())
                    .setRecordPhone(merchantModel.getRecordPhone())
                    .setRecordRealName(merchantModel.getRecordRealName());
            //商品详情
            orderInformationDetailModel
                    .setInventoryCode(inventory.getInventoryCode())
                    .setInventoryName(inventory.getInventoryName())
                    .setInventoryInformation(inventory.getInventoryInformation())
                    .setInventoryPrice(inventory.getInventoryPrice());
            //接收订单人的详情
            orderInformationDetailModel
                    .setUserName(authorityInformationModel.getAuthorityUsername())
                    .setAuthorityName(authorityInformationModel.getAuthorityName())
                    .setRealName(authorityInformationModel.getRealName())
                    .setPhone(authorityInformationModel.getPhone());
        }else {
            throw new Exception("fegin error");
        }
        return orderInformationDetailModel;
    }

    @Override
    @Transactional
    public void sendInventory(SendInventoryVO sendInventoryVO) {
        OrderInformation orderInformation = new OrderInformation();
        //订单状态更改为已发货
        orderInformation
                .setOrderId(sendInventoryVO.getOrderId())
                .setSts("3");
        this.updateById(orderInformation);
        OrderPhysicalDistribution orderPhysicalDistribution = new OrderPhysicalDistribution();
        BeanUtils.copyProperties(sendInventoryVO, orderPhysicalDistribution);
        orderPhysicalDistribution
                .setSts("wait_courier")
                .setOrderPhysicalDistributionCode(UUID.randomUUID().toString());
        orderPhysicalDistributionService.save(orderPhysicalDistribution);
    }

    @Override
    @Transactional
    public void orderEnd(String id) {
        OrderInformation orderInformation = new OrderInformation();
        orderInformation
                .setOrderId(id)
                .setSts("2");
        this.updateById(orderInformation);
    }


    @Override
    @Transactional
    public PhysicalDetailModel getPhysicalDetail(String orderId) {

        PhysicalDetailModel physicalDetailModel = orderInformationMapper.getPhyDetail(orderId);
        List<PhysicalDistribution> list = physicalDistributionService.getByOrderPhysicalId(physicalDetailModel.getOrderPhysicalDistributionId());
        if (list != null && !list.isEmpty()){
            List<PhysicalDetailModel.Phy> list1 = list.stream().map(e->{
                PhysicalDetailModel.Phy phy = physicalDetailModel.getPhyC();
                phy.setCreateTime(e.getCreateTime());
                phy.setNowPositionName(e.getNowPositionName());
                phy.setNextPositionName(e.getNextPositionName());
                return phy;
            }).collect(Collectors.toList());
            physicalDetailModel.setPhyList(list1);
        }
        return physicalDetailModel;
    }
}
