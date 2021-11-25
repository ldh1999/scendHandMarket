package com.ldh.modules.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.ShopTrolley;
import com.ldh.modules.inventory.model.ShopTrolleyClientModel;
import org.apache.ibatis.annotations.Param;

public interface ShopTrolleyService extends IService<ShopTrolley> {
    Page<ShopTrolleyClientModel> listToClient(Page page, QueryWrapper queryWrapper, ShopTrolley shopTrolley) throws Exception;

    Integer countByUserIdAndInventoryId(String userId, String inventoryId);

    Integer updateBySyn(@Param("shopTrolley") ShopTrolley shopTrolley);

    ShopTrolleyClientModel selectByUserIdAndInventoryId(@Param("shopTrolley") ShopTrolley shopTrolley);

}
