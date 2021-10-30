package com.ldh.modules.authority.controller;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysUserEntity;
import com.ldh.modules.authority.model.AuthorityInformationModel;
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
        Result<AuthorityInformation> result = new Result<>();

        try{
            AuthorityInformationModel authorityInformation = authorityInformationService.findByUserName(username);
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
                redisTemplate.opsForValue().set(token, token,20, TimeUnit.MINUTES);
                authorityInformation.setToken(token);
                result.setResult(authorityInformation);
                result.succcess("登陆成功");
            }
        }catch (Exception e){
            result.error("error");
            log.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "注册", notes = "注册")
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public Result<?> register(@RequestBody AuthorityInformation authorityInformation){
        Result<?> result = new Result<>();
        try {
            if(authorityInformationService.countUserName(authorityInformation)>0){
                result.error("改用户名已被占用");
                return result;
            }
            authorityInformationService.register(authorityInformation);
            result.succcess("注册成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }
}
