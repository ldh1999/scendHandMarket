package com.ldh.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.sysService.client.SysDictClient;
import com.ldh.sysService.client.SysDictItemClient;
import com.ldh.sysService.pojo.SysDict;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("Sys/dict")
@Api(tags = "字典")
public class SysDictController {

    @Autowired
    private SysDictClient sysDictClient;

    @ApiOperation(value="字典列表", notes="字典列表")
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(SysDict sysDict,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return sysDictClient.list(sysDict, pageNo, pageSize, column, order);
    }


    @ApiOperation(value="添加字典", notes="添加字典")
    @PreAuthorize("hasAuthority('superAdmin')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysDict sysDict){
        return sysDictClient.add(sysDict);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典删除", notes="字典删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){
        return sysDictClient.deleteById(id);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典修改", notes="字典修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody SysDict sysDict){
        return sysDictClient.updateById(sysDict);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @ApiOperation(value="字典查找", notes="字典查找")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(value = "id", required = true)String id){
        return sysDictClient.selectById(id);
    }

    /**
     * 获取全部字典数据
     *
     * @return
     */
    @PreAuthorize("hasAuthority('superAdmin,user,admin')")
    @RequestMapping(value = "/queryAllDictItems", method = RequestMethod.GET)
    public Result<?> queryAllDictItems(HttpServletRequest request) {
        return sysDictClient.queryAllDictItems();
    }
}
