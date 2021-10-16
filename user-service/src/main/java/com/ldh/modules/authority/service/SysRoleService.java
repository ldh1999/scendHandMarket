package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.SysRole;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> list(Page page, QueryWrapper queryWrapper, SysRole sysRole);
}
