package com.ldh.modules.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.model.InventoryCategoryAssociateModel;

import java.util.List;

public interface InventoryCategoryAssociateService extends IService<InventoryCategoryAssociate> {

    IPage<InventoryCategoryAssociateModel> list(Page page, QueryWrapper queryWrapper, InventoryCategoryAssociate inventoryCategoryAssociate);

    Integer deleteByCategoryId(String categoryId);

    Integer deleteByInventoryId(String inventoryId);

    List<InventoryCategoryAssociateModel> getByInventoryId(String id);
}
