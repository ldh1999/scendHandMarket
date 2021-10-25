package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryCategoryMapper extends BaseMapper<InventoryCategory> {

    IPage<InventoryCategoryVO> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory);
}
