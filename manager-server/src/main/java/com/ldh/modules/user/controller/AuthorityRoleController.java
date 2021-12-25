package com.ldh.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.userService.client.AuthorityRoleInformationClient;
import com.ldh.userService.model.AuthorityRoleInformationModel;
import com.ldh.userService.pojo.AuthorityInformation;
import com.ldh.userService.pojo.AuthorityRoleInformation;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("用户权限表")
@RequestMapping("User/UserRole")
public class AuthorityRoleController {


    @Autowired
    private AuthorityRoleInformationClient authorityRoleInformationClient;

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户权限列表", notes="用户权限列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(AuthorityRoleInformation authorityInformation,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return authorityRoleInformationClient.list(authorityInformation, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户权限添加", notes="用户权限添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody AuthorityRoleInformation authorityRoleInformation){

        return authorityRoleInformationClient.insert(authorityRoleInformation);
    }
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="用户权限删除", notes="用户权限删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        return authorityRoleInformationClient.deleteById(id);
    }
}
