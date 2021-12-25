package com.ldh.modules.sys.controller;

import com.ldh.sysService.client.SysDictItemClient;
import com.ldh.sysService.pojo.SysDictItem;
import common.OptionModel;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("字典项")
@RestController
@RequestMapping(path = "Sys/dictItem")
public class SysDictItemController {

    @Autowired
    private SysDictItemClient sysDictItemClient;

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典项列表", notes="字典项列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysDictItem sysDictItem,
                          @RequestParam(name = "dictId") String dictId,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return sysDictItemClient.list(sysDictItem, dictId, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典项增加", notes="字典项增加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody SysDictItem sysDictItem){
        return sysDictItemClient.insert(sysDictItem);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典项删除", notes="字典项删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){
        return sysDictItemClient.deleteById(id);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="根据字典项和字典表查值", notes="根据字典项和字典表查值")
    @RequestMapping(path = "/getDictItem", method = RequestMethod.GET)
    public Result<?> getItemValueBydictNoAndItemKey(@RequestParam(value = "dictNo", required = true)String dictNo, @RequestParam(value = "itemKey", required = true)String itemKey){
        return sysDictItemClient.getItemValueBydictNoAndItemKey(dictNo,itemKey);
    }
    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="跟胡字典编码获取字典项item", notes="跟胡字典编码获取字典项item")
    @RequestMapping(path = "/getOptionByDictNo", method = RequestMethod.GET)
    public Result<?> getOptionByDictNo(@RequestParam(value = "dictNo", required = true)String dictNo){
        return sysDictItemClient.getOptionByDictNo(dictNo);
    }
}
