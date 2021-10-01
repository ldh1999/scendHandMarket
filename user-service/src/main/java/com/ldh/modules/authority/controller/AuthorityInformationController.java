package com.ldh.modules.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.service.AuthorityInformationService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags="用户模块")
@Controller
@RestController
@RequestMapping("User/AuthorityInformation")
public class AuthorityInformationController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @ApiOperation(value="用户管理列表", notes="用户管理列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(AuthorityInformation authorityInformation,
                       @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                       @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                       @RequestParam(name="column", required = false) String column,
                       @RequestParam(name="order", required = false) String order){
        Page<AuthorityInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AuthorityInformation> queryWrapper =  new QueryWrapper<>();
        Result<IPage<AuthorityInformation>> result = new Result<>();
        try{
            IPage<AuthorityInformation> ipage = authorityInformationService.list(authorityInformation,page,queryWrapper);
            result.succcess(ipage);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="用户管理添加", notes="用户管理添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody AuthorityInformation authorityInformation){
        Result<?> result = new Result<>();
        try {
            if (authorityInformationService.countUserName(authorityInformation.getAuthorityUsername())>0){
                result.error("该用户已存在");
                return result;
            }
            authorityInformationService.save(authorityInformation);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }


}
