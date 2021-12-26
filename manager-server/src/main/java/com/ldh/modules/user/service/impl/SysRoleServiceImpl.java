package com.ldh.modules.user.service.impl;

import com.ldh.modules.user.service.SysRoleService;
import com.ldh.userService.client.SysRoleClient;
import com.ldh.userService.pojo.SysRole;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleClient sysRoleClient;

    @Override
    public Result<?> list(SysRole sysRole, Integer pageNo, Integer pageSize, String column, String order) {
        return sysRoleClient.list(sysRole, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> delete(String id) {
        return sysRoleClient.delete(id);

    }

    @Override
    public Result<?> add(SysRole sysRole) {
        return sysRoleClient.add(sysRole);

    }

    @Override
    public Result<?> update(SysRole sysRole) {
        return sysRoleClient.update(sysRole);

    }
}
