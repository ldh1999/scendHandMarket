package com.ldh.modules.user.controller;

import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.pojo.AuthorityInformation;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags="用户模块")
@RestController
@RequestMapping("User/AuthorityInformation")
public class AuthorityInformationController {

    @Autowired
    private AuthorityClient authorityClient;

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户管理列表", notes="用户管理列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(AuthorityInformation authorityInformation,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return authorityClient.list(authorityInformation, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户管理添加", notes="用户管理添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody AuthorityInformation authorityInformation){

        return authorityClient.insert(authorityInformation);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户管理修改", notes="用户管理修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody AuthorityInformation authorityInformation){

        return authorityClient.updateById(authorityInformation);
    }
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户管理删除", notes="用户管理删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        return authorityClient.deleteById(id);
    }
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户管理查找", notes="用户管理查找")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(value = "id", required = true)String id){

        return authorityClient.selectById(id);
    }

}
