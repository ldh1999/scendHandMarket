package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import common.OptionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryCategoryMapper extends BaseMapper<InventoryCategory> {

    IPage<InventoryCategoryModel> list(Page page, QueryWrapper queryWrapper, @Param("inventoryCategory") InventoryCategory inventoryCategory);

    List<OptionModel> getAllOption(String fatherId);

    List<OptionModel> getOptionByFatherId(String fatherId);
    
    List<InventoryCategoryModel> getAllCategory();

    String getFatherCategoryIdsByInventoryId(String inventoryId);

    List<OptionModel> getAllOptionSF();

    Integer deleteByFatherId(String fatherId);
}
