package com.ldh.modules.inventory.service;

import com.ldh.inventoryService.pojo.Inventory;
import common.Result;

public interface InventoryService {

    Result<?> list(Inventory inventory, Integer pageNo,Integer pageSize, String column, String order);

    Result<?> deleteById(String id);
}
