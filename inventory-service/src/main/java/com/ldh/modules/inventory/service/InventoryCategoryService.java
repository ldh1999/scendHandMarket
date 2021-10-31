package com.ldh.modules.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryModel;

public interface InventoryCategoryService extends IService<InventoryCategory> {

    IPage<InventoryCategoryModel> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory);

    void deleteAnyById(String id);
}
