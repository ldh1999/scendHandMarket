package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.mapper.InventoryMapper;
import com.ldh.modules.inventory.model.InventoryClientModel;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.model.InventoryRecommendModel;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.userService.client.MerchantClient;
import com.ldh.userService.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private InventoryCategoryAssociateMapper inventoryCategoryAssociateMapper;

    @Override
    public IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, Inventory inventory) {

        IPage<InventoryModel> iPage = inventoryMapper.list(page, queryWrapper, inventory);
        List<InventoryModel> list = iPage.getRecords();
        list.stream().forEach(e->{
            Merchant merchant = merchantClient.selectById(e.getId()).getResult();
            if (merchant != null){
                e.setMerchantName(merchant.getMerchantName());
            }
        });
        iPage.setRecords(list);

        return iPage;
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



        return iPage;
    }
}
