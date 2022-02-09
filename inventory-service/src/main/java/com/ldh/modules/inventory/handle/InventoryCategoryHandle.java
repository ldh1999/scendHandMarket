package com.ldh.modules.inventory.handle;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("inventory/inventoryCategory/handle")
public class InventoryCategoryHandle {

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @ApiOperation(value="商品分类列表", notes="商品分类列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(InventoryCategory inventoryCategory,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<InventoryCategory> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<InventoryCategoryModel> iPage = inventoryCategoryService.list(page, queryWrapper, inventoryCategory);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品分类添加", notes="商品分类添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody InventoryCategory inventoryCategory){
        Result<?> result = new Result<>();
        try {
            inventoryCategoryService.save(inventoryCategory);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("增加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="商品分类修改", notes="商品分类修改")
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Result<?> update(@RequestBody InventoryCategory inventoryCategory){
        Result<?> result = new Result<>();
        try {
            inventoryCategoryService.updateById(inventoryCategory);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="商品分类删除", notes="商品分类删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            //TODO
            inventoryCategoryService.deleteAnyById(id);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }
}
