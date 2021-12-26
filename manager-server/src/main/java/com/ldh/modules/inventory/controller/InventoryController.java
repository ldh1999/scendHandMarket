package com.ldh.modules.inventory.controller;

import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.modules.inventory.service.InventoryService;
import common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("inventory/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "商品列表", notes = "商品列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(Inventory inventory,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "column", required = false) String column,
                          @RequestParam(name = "order", required = false) String order) {
       return inventoryService.list(inventory, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "商品删除", notes = "商品删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true) String id) {

        return inventoryService.deleteById(id);
    }

}
