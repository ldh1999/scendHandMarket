package com.ldh.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.entity.SysHorseLamp;
import com.ldh.modules.sys.model.SysHorseLampModel;
import com.ldh.modules.sys.service.SysHorseLampService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("走马灯")
@RestController
@RequestMapping(path = "sys/horseLamp")
public class SysHorseLampController {


    @Autowired
    private SysHorseLampService sysHorseLampService;


    @ApiOperation(value="走马灯列表", notes="走马灯列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysHorseLamp sysHorseLamp,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<SysHorseLamp> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<SysHorseLampModel> iPage = sysHorseLampService.list(page, queryWrapper, sysHorseLamp);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="添加走马灯", notes="添加走马灯")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysHorseLamp sysHorseLamp){
        Result<?> result = new Result<>();

        try{
            sysHorseLampService.save(sysHorseLamp);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.toString());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="走马灯删除", notes="走马灯删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            sysHorseLampService.removeById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value="走马灯修改", notes="走马灯修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody SysHorseLamp sysHorseLamp){

        Result<?> result = new Result<>();

        try{
            sysHorseLampService.updateById(sysHorseLamp);
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("修改失败");
        }
        return result;
    }
}
