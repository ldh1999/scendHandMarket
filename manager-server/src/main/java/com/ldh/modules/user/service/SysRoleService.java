package com.ldh.modules.user.service;

import com.ldh.userService.pojo.SysRole;
import common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface SysRoleService {

    Result<?> list(SysRole sysRole, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> delete(String id);

    Result<?> add(SysRole sysRole);

    Result<?> update(SysRole sysRole);
}
