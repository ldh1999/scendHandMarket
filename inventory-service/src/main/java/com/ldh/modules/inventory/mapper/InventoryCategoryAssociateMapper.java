package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.model.InventoryCategoryAssociateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryCategoryAssociateMapper extends BaseMapper<InventoryCategoryAssociate> {

    IPage<InventoryCategoryAssociateModel> list(Page page, QueryWrapper queryWrapper,@Param("inventoryCategoryAssociate") InventoryCategoryAssociate inventoryCategoryAssociate);

    Integer deleteByCategoryId(String categoryId);

    Integer deleteByInventoryId(String inventoryId);
}
