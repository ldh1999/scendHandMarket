package com.ldh.modules.sys.controller;

import com.ldh.sysService.client.SysHorseLampClient;
import com.ldh.sysService.model.SysHorseLampVO;
import com.ldh.sysService.pojo.SysHorseLamp;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api("走马灯")
@RestController
@RequestMapping(path = "sys/horseLamp")
public class SysHorseLampController {


    @Autowired
    private SysHorseLampClient sysHorseLampClient;

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="走马灯列表", notes="走马灯列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysHorseLamp sysHorseLamp,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return sysHorseLampClient.list(sysHorseLamp, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="主页走马灯", notes="主页走马灯")
    @RequestMapping(path = "/homeListClient", method = RequestMethod.GET)
    public Result<?> homeListClient(){
        return sysHorseLampClient.homeListClient();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="添加走马灯", notes="添加走马灯")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysHorseLampVO sysHorseLampVO){
        return sysHorseLampClient.add(sysHorseLampVO);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="走马灯删除", notes="走马灯删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){
        return sysHorseLampClient.deleteById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="走马灯修改", notes="走马灯修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody SysHorseLamp sysHorseLamp){
        return sysHorseLampClient.updateById(sysHorseLamp);
    }


}
