package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.SysRole;
import com.ldh.modules.authority.mapper.SysRoleMapper;
import com.ldh.modules.authority.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> list(Page page, QueryWrapper queryWrapper, SysRole sysRole) {
        return sysRoleMapper.list(page, queryWrapper, sysRole);
    }
}
