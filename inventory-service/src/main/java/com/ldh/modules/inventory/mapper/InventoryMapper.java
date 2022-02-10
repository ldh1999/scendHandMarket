package com.ldh.modules.inventory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, @Param("inventory") Inventory inventory);

    IPage<InventoryClientModel> listToClient(Page page, QueryWrapper queryWrapper, @Param("inventory") Inventory inventory);

    IPage<InventoryRecommendModel> getRecommendList(Page page, String userId);

    InventoryClientModel selectByIdAll(String id);

    List<Inventory> selectByIds(@Param("ids") String[] ids);

    IPage<InventoryMerchantModel> listToClientByMerchant(Page page,
                                                        QueryWrapper queryWrapper,
                                                        @Param("inventory") Inventory inventory);

    IPage<InventoryCategoryClientModel> listToClientByCategory(Page page,
                                                               QueryWrapper queryWrapper,
                                                               @Param("inventoryCategory") InventoryCategory inventoryCategory);

    List<AutoSearchResponse> getSearchLimit(@Param("str") String str, @Param("num") Integer num);



    IPage<InventoryRecommendModel> getRandInventory(Page page);

}
