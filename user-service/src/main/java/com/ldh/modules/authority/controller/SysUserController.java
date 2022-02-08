package com.ldh.modules.authority.controller;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.ldh.constant.UserStsEnum;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysUserEntity;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.modules.authority.model.EditPasswordVO;
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
@Api(tags = "用户信息模块")
public class SysUserController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private RedisTemplate redisTemplate;

   /* @ApiOperation(value = "管理员登录", notes = "管理员登录")
    @RequestMapping(path = "/loginManager",method = RequestMethod.GET)
    Result<?> loginManager(@RequestParam(name = "userName",required = true)String username,
                    @RequestParam(name = "passWord",required = true)String password,
                    HttpServletRequest request){
        Result<AuthorityInformation> result = new Result<>();

        try{
            AuthorityInformationModel authorityInformation = authorityInformationService.findByUserName(username,request);
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
                redisTemplate.opsForValue().set(authorityInformation.getAuthorityId(), token,20, TimeUnit.MINUTES);
                authorityInformation.setToken(token);
                result.setResult(authorityInformation);
                result.succcess("登陆成功");
            }
        }catch (Exception e){
            result.error("error");
            log.error(e.getMessage());
        }
        return result;
    }*/


    @ApiOperation(value = "普通用户登录", notes = "普通用户登录")
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    Result<?> login(@RequestParam(name = "userName",required = true)String username,
                   @RequestParam(name = "passWord",required = true)String password,
                   HttpServletRequest request){

        Result<AuthorityInformation> result = new Result<>();
        AuthorityInformationModel authorityInformation = authorityInformationService.findByUserName(username,request);

        try{
            if (authorityInformation == null){
                result.error("用户不存在");
            }else if (!authorityInformation.getAuthorityPassword().equals(password)){
                result.error("密码错误");
            }else if (authorityInformation.getSts().equals(UserStsEnum.freeze.getUserSts())){
                result.error("该账号已冻结");
            }else {
                HttpSession session = request.getSession();
                String token = UuidUtils.generateUuid();
                session.removeAttribute("user");
                session.setAttribute("user", authorityInformation);
                redisTemplate.opsForValue().set(authorityInformation.getAuthorityId(), token,20, TimeUnit.MINUTES);
                authorityInformation.setToken(token);
                result.setResult(authorityInformation);
                result.succcess("登陆成功");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;

    }


    @ApiOperation(value = "注册", notes = "注册")
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public Result<?> register(@RequestBody AuthorityInformation authorityInformation){
        Result<?> result = new Result<>();
        try {
            if(authorityInformationService.countUserName(authorityInformation)>0){
                result.error("该用户名已被占用");
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



    @ApiOperation(value = "自动登录", notes = "自动登录")
    @RequestMapping(path = "/autoLogin",method = RequestMethod.POST)
    public Result<?> autoLogin(@RequestBody AuthorityInformation authorityInformation,
                               HttpServletRequest request){
        Result<AuthorityInformationModel> result = new Result<>();
        try {
            AuthorityInformationModel authorityInformationModel = authorityInformationService.findByUserName(authorityInformation.getAuthorityUsername(),request);
            if (authorityInformationModel == null ||
                    !authorityInformation.getAuthorityPassword().equals(authorityInformationModel.getAuthorityPassword())){
                throw new Exception("登录信息已过期，请重新登录");
            }
            HttpSession session = request.getSession();
            String token = UuidUtils.generateUuid();
            authorityInformationModel.setToken(token);
            session.setAttribute("user", authorityInformationModel);
            result.setResult(authorityInformationModel);
            result.succcess("");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("登录信息已过期，请重新登录");
        }
        return result;
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(path = "/editPassword",method = RequestMethod.POST)
    public Result<?> autoLogin(@RequestBody EditPasswordVO editPasswordVO){
        Result<?> result = new Result<>();
        try{
            AuthorityInformation authorityInformation = authorityInformationService.getById(editPasswordVO.getAuthorityId());
            if (!authorityInformation.getAuthorityPassword().equals(editPasswordVO.getOriginPassword())){
                result.error("原始密码不对");
                return result;
            }
            AuthorityInformation authorityInformationNow = new AuthorityInformation();
            authorityInformationNow.setAuthorityId(editPasswordVO.getAuthorityId());
            authorityInformationNow.setAuthorityPassword(editPasswordVO.getPassword());
            authorityInformationService.updateById(authorityInformationNow);
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "注销当前登陆人", notes = "注销当前登陆人")
    @RequestMapping(path = "/logoutNowUser",method = RequestMethod.GET)
    public Result<?> logoutNowUser(HttpServletRequest request){
        Result<?> result = new Result<>();
        HttpSession session = request.getSession();
        try{
            session.removeAttribute("user");
            result.succcess("注销成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

}
