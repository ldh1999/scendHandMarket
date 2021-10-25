package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.mapper.AuthorityRoleMapper;
import com.ldh.modules.authority.model.AuthorityRoleInformationModel;
import com.ldh.modules.authority.service.AuthorityRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityRoleServiceImpl extends ServiceImpl<AuthorityRoleMapper, AuthorityRoleInformation> implements AuthorityRoleService {

    @Autowired
    private AuthorityRoleMapper authorityRoleMapper;

    @Override
    public IPage<AuthorityRoleInformationModel> list(Page page, QueryWrapper queryWrapper, AuthorityRoleInformation authorityRoleInformation) {
        return authorityRoleMapper.list(page, queryWrapper, authorityRoleInformation);
    }

    @Override
    public Integer countAuthorityRoleByAuthorityId(String authorityId, String sysRoleId) {
        return authorityRoleMapper.countAuthorityRoleByAuthorityId(authorityId, sysRoleId);
    }

    @Override
    public Integer deleteByAuthorityId(String authorityId) {
        return authorityRoleMapper.deleteByAuthorityId(authorityId);
    }
}
