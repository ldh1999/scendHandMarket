package com.ldh.modules.inventory.service.impl;

import com.ldh.inventoryService.client.InventoryCategoryClient;
import com.ldh.inventoryService.pojo.InventoryCategory;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.security.entity.SysUserEntity;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InventoryCategoryServiceImpl implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryClient inventoryCategoryClient;

    @Override
    public Result<?> list(InventoryCategory inventoryCategory, Integer pageNo, Integer pageSize, String column, String order) {
        return inventoryCategoryClient.list(inventoryCategory, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> insert(InventoryCategory inventoryCategory) {
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        inventoryCategory.setCreateBy(sysUserEntity.getUserId());
        return inventoryCategoryClient.insert(inventoryCategory);
    }

    @Override
    public Result<?> update(InventoryCategory inventoryCategory) {
        return inventoryCategoryClient.update(inventoryCategory);
    }

    @Override
    public Result<?> deleteById(String id) {
        return inventoryCategoryClient.deleteById(id);

    }
}
