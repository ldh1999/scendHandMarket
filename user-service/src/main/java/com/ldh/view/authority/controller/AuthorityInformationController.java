package com.ldh.view.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.view.authority.entity.AuthorityInformation;
import com.ldh.view.authority.service.AuthorityInformationService;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController("/User/AuthorityInformation")
public class AuthorityInformationController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @RequestMapping(name = "list", method = RequestMethod.GET)
    public Result<?> list(AuthorityInformation authorityInformation,
                       @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                       @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                       @RequestParam(name="column") String column,
                       @RequestParam(name="order") String order){
        Page<AuthorityInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AuthorityInformation> queryWrapper =  new QueryWrapper<>();

        Result<AuthorityInformation> result = new Result<>();


        return result;

    }
}
