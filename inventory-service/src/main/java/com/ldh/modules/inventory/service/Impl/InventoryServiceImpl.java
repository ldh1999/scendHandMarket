package com.ldh.modules.inventory.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.mapper.InventoryMapper;
import com.ldh.modules.inventory.model.*;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import common.Result;
import constant.UploadFileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private InventoryCategoryAssociateMapper inventoryCategoryAssociateMapper;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Override
    public IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        return inventoryMapper.list(page, queryWrapper, inventory);
    }

    @Override
    public void deleteAnyById(String id) {
        this.removeById(id);
        inventoryCategoryAssociateMapper.deleteByInventoryId(id);
    }

    @Override
    public IPage<InventoryClientModel> listToClient(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        return inventoryMapper.listToClient(page, queryWrapper, inventory);
    }

    @Override
    public IPage<InventoryRecommendModel> getRecommendList(Page page) {
        IPage<InventoryRecommendModel> iPage = inventoryMapper.getRecommendList(page);
        //TODO
        return iPage;
    }

    @Override
    public InventoryClientModel getByIdAll(String id) throws Exception {
        InventoryClientModel inventoryClientModel = inventoryMapper.selectByIdAll(id);
        ImageGetVO imageGetVO = new ImageGetVO();
        imageGetVO.setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                .setObjectId(id);
        Result<List<ImageNoteModel>> result = imageNoteGetClient.getByObjectIdAndImgGroup(imageGetVO);
        if (result.isSuccess()){
            List<ImageNoteModel> list = result.getResult();
            List<String> imgPaths = new LinkedList<>();
            if (list != null){
                list.stream().forEach(e->{
                    imgPaths.add(e.getImgPath());
                });
                inventoryClientModel.setImageListUrl(imgPaths);
            }
        }else {
            throw new Exception("inventory fegin error");
        }
        return inventoryClientModel;
    }

    @Override
    public List<Inventory> selectByIds(String[] ids) {
        return inventoryMapper.selectByIds(ids);
    }

    @Override
    public IPage<InventoryMerchantModel> listToClientByMerchant(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        return inventoryMapper.listToClientByMerchant(page, queryWrapper, inventory);
    }

    @Override
    public IPage<InventoryCategoryClientModel> listToClientByCategory(Page page, QueryWrapper queryWrapper, String categoryId) {
        return inventoryMapper.listToClientByCategory(page, queryWrapper, categoryId);
    }
}
