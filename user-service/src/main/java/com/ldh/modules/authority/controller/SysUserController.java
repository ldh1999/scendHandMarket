package com.ldh.modules.authority.controller;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.service.AuthorityInformationService;
import com.ldh.modules.authority.service.SysRoleService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("sys/user")
@Slf4j
@Api(tags = "登录模块")
public class SysUserController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    Result<?> login(@RequestParam(name = "userName",required = true)String username,
                    @RequestParam(name = "passWord",required = true)String password,
                    HttpServletRequest request){
        Result<?> result = new Result<>();

        try{
            AuthorityInformation authorityInformation = authorityInformationService.findByUserName(username);
            if (authorityInformation == null){
                result.error("用户不存在");
            }else if (!authorityInformation.getAuthorityPassword().equals(password)){
                result.error("密码错误");
            }else if (!sysRoleService.scanManagerRoleByUserId(authorityInformation.getAuthorityId()).isSuccess())
                result = sysRoleService.scanManagerRoleByUserId(authorityInformation.getAuthorityId());
            else {
                HttpSession session = request.getSession();
                String token = UuidUtils.generateUuid();
                session.setAttribute("user", authorityInformation);
                session.setAttribute("token", token);
                redisTemplate.opsForValue().set(token, token,20, TimeUnit.MINUTES);
                result.succcess("登陆成功");
            }
        }catch (Exception e){
            result.error("error");
            log.error(e.getMessage());
        }
        return result;
    }
}
