package com.ldh.modules.inventory.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.service.InventoryService;
import common.Result;
import common.StringTo;
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

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(Inventory inventory,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "column", required = false) String column,
                          @RequestParam(name = "order", required = false) String order) {
        Result<IPage> result = new Result<>();
        Page<InventoryModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (order != null) {
            if (order.equals("desc")) {
                queryWrapper.orderByDesc(StringTo.humpToLine(column));
            } else {
                queryWrapper.orderByAsc(StringTo.humpToLine(column));
            }
        }
        try {
            IPage<InventoryModel> iPage = inventoryService.list(page, queryWrapper, inventory);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value = "商品删除", notes = "商品删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true) String id) {

        Result<?> result = new Result<>();
        try {
            //TODO 暂时此接口针对客户端  做伪删除
//            inventoryService.deleteAnyById(id);
            Inventory inventory = new Inventory();
            inventory.setId(id);
            inventory.setSts("-1");
            inventoryService.updateById(inventory);
            result.succcess("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }


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
