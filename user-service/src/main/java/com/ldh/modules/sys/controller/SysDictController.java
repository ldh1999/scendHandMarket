package com.ldh.modules.sys.controller;

import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.sys.entity.SysDict;
import com.ldh.modules.sys.service.SysDictService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.datatransfer.SystemFlavorMap;

@RestController
@Slf4j
@RequestMapping("Sys/dict")
@Api(tags = "字典")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @ApiOperation(value="添加字典", notes="添加字典")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysDict sysDict){
        Result<?> result = new Result<>();

        if (sysDictService.countByNo(sysDict.getDictNo())>0){
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
    @RequestMapping(path = "/deleteById", method = RequestMethod.GET)
    public Result<?> updateById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            sysDictService.removeById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value="用户管理查找", notes="用户管理查找")
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
