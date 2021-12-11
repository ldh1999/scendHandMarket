package com.ldh.modules.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryClientModel;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import common.OptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InventoryCategoryService extends IService<InventoryCategory> {

    IPage<InventoryCategoryModel> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory);

    void deleteAnyById(String id);

    List<OptionModel> getAllOption();

    boolean setAllCategoryToRedis();

    Map<String, InventoryCategoryModel> getAllCategoryToRedis();

}
