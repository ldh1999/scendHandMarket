package com.ldh.modules.inventory.service;

import com.ldh.inventoryService.pojo.InventoryCategoryAssociate;
import common.Result;

public interface InventoryCategoryAssociateService {

    Result<?> list(InventoryCategoryAssociate inventoryCategoryAssociate, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> deleteById(String id);
}
