package com.ldh.modules.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.model.InventoryClientModel;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.model.InventoryRecommendModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryService extends IService<Inventory> {

    IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, Inventory inventory);

    IPage<InventoryClientModel> listToClient(Page page, QueryWrapper queryWrapper, @Param("inventory") Inventory inventory);

    void deleteAnyById(String id);

    IPage<InventoryRecommendModel> getRecommendList(Page page);

    InventoryClientModel getByIdAll(String id) throws Exception;

    List<Inventory> selectByIds(String[] ids);

}
