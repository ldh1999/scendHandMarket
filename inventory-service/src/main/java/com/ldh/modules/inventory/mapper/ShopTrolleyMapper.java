package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.ShopTrolley;
import com.ldh.modules.inventory.model.ShopTrolleyClientModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopTrolleyMapper extends BaseMapper<ShopTrolley> {
    Page<ShopTrolleyClientModel> listToClient(Page page, @Param("shopTrolley") ShopTrolley shopTrolley, QueryWrapper queryWrapper);

    Integer countByUserIdAndInventoryId(String userId, String inventoryId);

    Integer updateBySyn(@Param("shopTrolley") ShopTrolley shopTrolley);

    ShopTrolleyClientModel selectByUserIdAndInventoryId(@Param("shopTrolley") ShopTrolley shopTrolley);
}
