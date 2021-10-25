package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysRole;
import common.OptionModel;
import common.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> list(Page page, QueryWrapper queryWrapper, SysRole sysRole);

    List<OptionModel> getAllOption();

    Integer countByNo(SysRole sysRole);

    Result<?> deleteByIdScan(String id, HttpServletRequest request);

    Result<AuthorityInformation> scanManagerRoleByUserId(String userId);
}
