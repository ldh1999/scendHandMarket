package com.ldh.modules.inventory.handle;

import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.service.InventoryService;
import common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("inventory/handle")
public class InventoryHandle {

    @Autowired
    private InventoryService inventoryService;

    @ApiOperation(value = "根据id查商品", notes = "根据id查商品")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(name = "id")String id) {
        Result<Inventory> result = new Result<>();
        try{
            result.setResult(inventoryService.getById(id));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据许多id查商品", notes = "根据许多id查商品")
    @RequestMapping(path = "/selectByIds", method = RequestMethod.GET)
    public Result<?> selectByIds(@RequestParam(name = "ids")String ids) {
            Result<List<?>> result = new Result<>();
        try{
            result.setResult(inventoryService.selectByIds(ids.split(",")));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }




}
