package com.ldh.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.userService.client.SysRoleClient;
import com.ldh.userService.pojo.AuthorityInformation;
import com.ldh.userService.pojo.SysRole;
import common.OptionModel;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "User/Role")
@Api("用户权限管理")
public class SysRoleController {

    @Autowired
    private SysRoleClient sysRoleClient;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(path = "list",method = RequestMethod.GET)
    public Result<?> list(SysRole sysRole,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){

        return sysRoleClient.list(sysRole, pageNo, pageSize, column, order);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @RequestMapping(path = "delete" , method = RequestMethod.DELETE)
    public Result<?> delete(@RequestParam(name="id")String id, HttpServletRequest request){

        return sysRoleClient.delete(id);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @RequestMapping(path = "add" , method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysRole sysRole){

        return sysRoleClient.add(sysRole);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @RequestMapping(path = "updateById" , method = RequestMethod.POST)
    public Result<?> update(@RequestBody SysRole sysRole){

        return sysRoleClient.update(sysRole);
    }

}
