package com.ldh.modules.user.controller;

import User.AuthorityInformation;
import com.ldh.modules.order.service.CourierService;
import com.ldh.modules.user.model.CourierOrderInformationModel;
import com.ldh.modules.user.model.ManagementInformationModel;
import com.ldh.modules.user.service.AuthorityInformationService;
import com.ldh.modules.user.service.SysUserService;
import com.ldh.orderService.client.CourierClient;
import com.ldh.orderService.client.OrderInformationClient;
import com.ldh.orderService.model.CourierModel;
import com.ldh.orderService.pojo.Courier;
import com.ldh.security.entity.SysUserEntity;
import com.ldh.userService.client.AuthorityRoleInformationClient;
import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import common.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@Slf4j
@RequestMapping(path = "sys/user")
@Api("管理员用户信息")
public class SysUser {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @Autowired
    private OrderInformationClient orderInformationClient;

    @Autowired
    private AuthorityRoleInformationClient authorityRoleInformationClient;

    @Autowired
    private CourierService courierService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前管理员信息
     */
    @GetMapping("getNowUser")
    @PreAuthorize("hasAuthority('admin')")
    public Result<AuthorityInformation> getNowUser(){
        Result result = new Result();
        try{
            SysUserEntity sysUserEntity = (SysUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Object object = authorityInformationService.selectById(sysUserEntity.getUserId()).getResult();
            AuthorityInformation authorityInformation = new AuthorityInformation();
            BeanUtils.copyProperties(object, authorityInformation);
            authorityInformation.setAuthorityPassword("******");
            result.setResult(authorityInformation);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 获取当前快递员信息
     */
    @GetMapping("getNowCourierUser")
    @PreAuthorize("hasAuthority('courier')")
    public Result<CourierModel> getNowCourier(){
        Result result = new Result();
        try{
            SysUserEntity sysUserEntity = (SysUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            result.setResult(courierService.queryAllById(sysUserEntity.getUsername()).getResult());
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 快递员界面首页信息
     * @return
     */
    @GetMapping("getCourierOrderInformationModel")
    @PreAuthorize("hasAuthority('courier')")
    public Result<CourierOrderInformationModel> getCourierOrderInformationModel(){
        Result result = new Result();
        try {
            result.setResult(sysUserService.getCourierOrderInformation());
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


    /**
     * 管理员界面首页信息
     * @return
     */
    @GetMapping("getInformationForManagement")
    @PreAuthorize("hasAuthority('admin')")
    public Result<ManagementInformationModel> getInformationForManagement(){
        Result result = new Result();
        try {
            result.setResult(sysUserService.getInformationForManagement());
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }
}
