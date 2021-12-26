package com.ldh.modules.inventory.service.impl;

import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.modules.inventory.service.InventoryService;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public Result<?> list(Inventory inventory, Integer pageNo, Integer pageSize, String column, String order) {
        return inventoryClient.list(inventory, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> deleteById(String id) {
        return inventoryClient.deleteById(id);
    }
}
