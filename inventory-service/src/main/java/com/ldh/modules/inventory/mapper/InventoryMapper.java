package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.model.InventoryClientModel;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.model.InventoryRecommendModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, @Param("inventory") Inventory inventory);

    IPage<InventoryClientModel> listToClient(Page page, QueryWrapper queryWrapper, @Param("inventory") Inventory inventory);

    IPage<InventoryRecommendModel> getRecommendList(Page page);
}
