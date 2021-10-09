package com.ldh.modules.authority.controller;

import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.service.AuthorityInformationService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("sys/user")
@Slf4j
@Api(tags = "登录模块")
public class SysUserController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

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
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("user", authorityInformation);
                result.succcess("登陆成功");
            }
        }catch (Exception e){
            result.error("error");
            log.error(e.getMessage());
        }
        return result;
    }
}
