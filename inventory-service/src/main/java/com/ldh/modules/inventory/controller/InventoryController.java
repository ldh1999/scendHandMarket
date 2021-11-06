package com.ldh.modules.inventory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.model.InventoryVO;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import com.ldh.modules.inventory.service.InventoryService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("inventory/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryCategoryAssociateService inventoryCategoryAssociateService;


    @ApiOperation(value="商品列表", notes="商品列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(Inventory inventory,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<InventoryModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (order!= null){
            if(order.equals("desc")){
                queryWrapper.orderByDesc(StringTo.humpToLine(column));
            }else{
                queryWrapper.orderByAsc(StringTo.humpToLine(column));
            }
        }
        try{
            IPage<InventoryModel> iPage = inventoryService.list(page, queryWrapper, inventory);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品删除", notes="商品删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            //TODO
            inventoryService.deleteAnyById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value="用户添加商品", notes="用户添加商品")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody InventoryVO inventoryVO){

        Result<?> result = new Result<>();
        String[] category = inventoryVO.getInventoryCategory();
        Inventory inventory = inventoryVO;

        try{
            inventoryService.save(inventory);
            Arrays.stream(category).forEach(e->{
                InventoryCategoryAssociate inventoryCategoryAssociate = new InventoryCategoryAssociate();
                inventoryCategoryAssociate.setInventoryCategoryId(e).
                        setInventoryId(inventoryVO.getMerchantId());
                inventoryCategoryAssociateService.save(inventoryCategoryAssociate);
            });
            result.succcess("添加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    @RequestMapping(path = "listToClient", method = RequestMethod.GET)
    public Result<?> listToClient(Inventory inventory,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){

        Result result = new Result();
        Page page = new Page(pageNo,pageSize);
        QueryWrapper<?> queryWrapper =  new QueryWrapper<>();
        try{
            result.setResult(inventoryService.listToClient(page, queryWrapper, inventory));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

}
