package com.ldh.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.service.SysDictItemService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("字典项")
@RestController
@RequestMapping(path = "Sys/dictItem")
public class SysDictItemController {

    @Autowired
    private SysDictItemService sysDictItemService;

    @ApiOperation(value="字典项列表", notes="字典项列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysDictItem sysDictItem,
                          @RequestParam(name = "dictId") String dictId,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<SysDictItem> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<SysDictItem> iPage = sysDictItemService.list(page, sysDictItem, queryWrapper, dictId);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="字典项增加", notes="字典项增加")
    @RequestMapping(path = "/add")
    public Result<?> insert(@RequestBody SysDictItem sysDictItem){
        Result<SysDictItem> result = new Result<>();

        try{
            if (sysDictItemService.countByItemKey(sysDictItem)>0){
                result.error("此key已存在");
                return result;
            }
            sysDictItemService.saveOrUpdate(sysDictItem);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="字典项删除", notes="字典项删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            sysDictItemService.removeById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }
}
