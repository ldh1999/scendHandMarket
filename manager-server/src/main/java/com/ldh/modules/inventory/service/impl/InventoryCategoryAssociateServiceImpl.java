package com.ldh.modules.inventory.service.impl;

import com.ldh.inventoryService.client.InventoryCategoryAssociateClient;
import com.ldh.inventoryService.pojo.InventoryCategoryAssociate;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryCategoryAssociateServiceImpl implements InventoryCategoryAssociateService {

    @Autowired
    private InventoryCategoryAssociateClient inventoryCategoryAssociateClient;

    @Override
    public Result<?> list(InventoryCategoryAssociate inventoryCategoryAssociate, Integer pageNo, Integer pageSize, String column, String order) {
        return inventoryCategoryAssociateClient.list(inventoryCategoryAssociate, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> deleteById(String id) {
        return inventoryCategoryAssociateClient.deleteById(id);
    }
}
