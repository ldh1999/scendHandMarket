package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysRole;
import com.ldh.modules.authority.mapper.AuthorityRoleMapper;
import com.ldh.modules.authority.mapper.SysRoleMapper;
import com.ldh.modules.authority.service.SysRoleService;
import common.OptionModel;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private AuthorityRoleMapper authorityRoleMapper;

    @Override
    public IPage<SysRole> list(Page page, QueryWrapper queryWrapper, SysRole sysRole) {
        return sysRoleMapper.list(page, queryWrapper, sysRole);
    }

    @Override
    public List<OptionModel> getAllOption() {
        return sysRoleMapper.getAllOption();
    }

    @Override
    public Integer countByNo(SysRole sysRole) {
        return sysRoleMapper.countByNo(sysRole);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void deleteByIdScan(String id, HttpServletRequest request) {
        this.removeById(id);
        authorityRoleMapper.deleteBySysRole(id);
    }

    /**
     * 当前用户是否有管理权限
     * @param userId
     * @return
     */
    @Override
    public Result<AuthorityInformation> scanManagerRoleByUserId(String userId) {
        Result<AuthorityInformation> result = new Result();
        if(sysRoleMapper.countRoleByUserIdAndRoleList(userId, Arrays.asList("superAdmin", "admin"))>0){
            result.setSuccess(true);
        }else {
            result.error("权限不足");
        }
        return result;
    }
}
