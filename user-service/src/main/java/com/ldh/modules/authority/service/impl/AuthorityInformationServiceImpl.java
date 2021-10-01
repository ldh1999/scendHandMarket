package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.mapper.AuthorityInformationMapper;
import com.ldh.modules.authority.service.AuthorityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityInformationServiceImpl extends ServiceImpl<AuthorityInformationMapper, AuthorityInformation> implements AuthorityInformationService {

    @Autowired
    private AuthorityInformationMapper authorityInformationMapper;

    @Override
    public IPage<AuthorityInformation> list(AuthorityInformation authorityInformation, Page page, QueryWrapper queryWrapper) {
        return authorityInformationMapper.list(authorityInformation,page,queryWrapper);
    }

    @Override
    public int countUserName(String userName) {
        return authorityInformationMapper.countUserName(userName);
    }
}
