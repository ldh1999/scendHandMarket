package com.ldh.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDict;
import com.ldh.modules.sys.service.SysDictService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("Sys/dict")
@Api(tags = "字典")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @ApiOperation(value="字典列表", notes="字典列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysDict sysDict,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<SysDict> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<SysDict> iPage = sysDictService.list(page, sysDict, queryWrapper);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }


    @ApiOperation(value="添加字典", notes="添加字典")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysDict sysDict){
        Result<?> result = new Result<>();

        if (sysDictService.countByNo(sysDict)>0){
            result.error("该字典编码已存在");
            return result;
        }
        try{
            sysDictService.save(sysDict);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.toString());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="字典删除", notes="字典删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            sysDictService.deleteByIdAnywhere(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value="字典修改", notes="字典修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody SysDict sysDict){

        Result<?> result = new Result<>();

        if (sysDictService.countByNo(sysDict)>0){
            result.error("该编码已存在");
            return result;
        }

        try{
            sysDictService.updateById(sysDict);
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("修改失败");
        }
        return result;
    }

    @ApiOperation(value="字典查找", notes="字典查找")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(value = "id", required = true)String id){
        Result<SysDict> result = new Result<>();
        try{
            result.setResult(sysDictService.getById(id));
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("操作失败");
        }
        return result;
    }
}
