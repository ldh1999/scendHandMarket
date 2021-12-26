package com.ldh.modules.inventory.service;

import com.ldh.inventoryService.pojo.InventoryCategory;
import common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface InventoryCategoryService {

    Result<?> list(InventoryCategory inventoryCategory, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> insert(InventoryCategory inventoryCategory);

    Result<?> update(InventoryCategory inventoryCategory);

    Result<?> deleteById(String id);
}
