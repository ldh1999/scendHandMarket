package com.ldh.modules.inventory.controller;

import com.ldh.inventoryService.client.InventoryCategoryClient;
import com.ldh.inventoryService.pojo.InventoryCategory;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.security.entity.SysUserEntity;
import common.Result;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("inventory/inventoryCategory")
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="商品分类列表", notes="商品分类列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(InventoryCategory inventoryCategory,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return inventoryCategoryService.list(inventoryCategory, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="商品分类添加", notes="商品分类添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody InventoryCategory inventoryCategory){
        SysUserEntity sysUserEntity = (SysUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        inventoryCategory.setCreateBy(sysUserEntity.getUserId());
        return inventoryCategoryService.insert(inventoryCategory);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="商品分类修改", notes="商品分类修改")
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Result<?> update(@RequestBody InventoryCategory inventoryCategory){

        return inventoryCategoryService.update(inventoryCategory);
    }


    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="商品分类删除", notes="商品分类删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        return inventoryCategoryService.deleteById(id);
    }



}
