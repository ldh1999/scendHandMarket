package com.ldh.modules.inventory.controller;

import com.ldh.inventoryService.client.InventoryCategoryAssociateClient;
import com.ldh.inventoryService.pojo.InventoryCategoryAssociate;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("商品分类关联")
@RequestMapping("inventory/inventoryCategoryAssociate")
public class InventoryCategoryAssociateController {

    @Autowired
    private InventoryCategoryAssociateClient inventoryCategoryAssociateClient;

    @ApiOperation(value="商品分类关联列表", notes="商品分类关联列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(InventoryCategoryAssociate inventoryCategoryAssociate,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){

        return inventoryCategoryAssociateClient.list(inventoryCategoryAssociate, pageNo, pageSize, column, order);
    }
    @ApiOperation(value="商品分类关联删除", notes="商品分类关联删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        return inventoryCategoryAssociateClient.deleteById(id);
    }
}
