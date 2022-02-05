package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.mapper.AuthorityInformationMapper;
import com.ldh.modules.authority.mapper.AuthorityRoleMapper;
import com.ldh.modules.authority.mapper.SysRoleMapper;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.modules.authority.service.AuthorityInformationService;
import com.ldh.modules.authority.service.AuthorityRoleService;
import constant.DefaultPath;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthorityInformationServiceImpl extends ServiceImpl<AuthorityInformationMapper, AuthorityInformation> implements AuthorityInformationService {

    @Autowired
    private AuthorityInformationMapper authorityInformationMapper;

    @Autowired
    private AuthorityRoleMapper authorityRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;



    @Override
    public IPage<AuthorityInformationModel> list(AuthorityInformation authorityInformation, Page page, QueryWrapper queryWrapper) {
        return authorityInformationMapper.list(page,authorityInformation,queryWrapper);
    }

    @Override
    public int countUserName(AuthorityInformation authorityInformation) {
        return authorityInformationMapper.countUserName(authorityInformation);
    }

    @Override
    public AuthorityInformationModel findByUserName(String username, HttpServletRequest request) {

        AuthorityInformationModel authorityInformationModel = authorityInformationMapper.findByUserName(username);
        return authorityInformationModel;
    }

    @Override
    @Transactional
    public void register(AuthorityInformation authorityInformation) {
        Random random = new Random();
        String userId = UUID.randomUUID().toString();
        if (StringUtils.isEmpty(authorityInformation.getAuthorityName())){
            String authorityName = "游客:"+random.nextInt();
            authorityInformation.setAuthorityName(authorityName);
        }
        String roleId = sysRoleMapper.selectByRoleNo("user").getId();
        authorityInformation.setAuthorityId(userId);
        authorityInformation.setImgPath(DefaultPath.DEFAULT_USER_IMG);
        AuthorityRoleInformation authorityRoleInformation= new AuthorityRoleInformation();
        authorityRoleInformation.setAuthorityId(userId);
        authorityRoleInformation.setSysRoleId(roleId);
        this.save(authorityInformation);
        authorityRoleMapper.insert(authorityRoleInformation);
    }

    @Override
    public List<AuthorityInformationModel> selectByIds(String[] ids) {
        List<AuthorityInformationModel> list = authorityInformationMapper.selectByIds(ids);
        return list;
    }

    @Override
    public void deleteAnyOneById(String id) {
        authorityInformationMapper.deleteById(id);
        authorityRoleMapper.deleteByAuthorityId(id);
    }

    @Override
    @Transactional
    public void registerCourier(AuthorityInformation authorityInformation) {
        String roleUserId = sysRoleMapper.selectByRoleNo("user").getId();
        String roleCourierId = sysRoleMapper.selectByRoleNo("courier").getId();

        String userId = UUID.randomUUID().toString();
        authorityInformation.setAuthorityId(userId);

        AuthorityRoleInformation authorityRoleInformation= new AuthorityRoleInformation();
        authorityRoleInformation.setAuthorityId(userId);
        authorityInformation.setImgPath(DefaultPath.DEFAULT_USER_IMG);
        authorityRoleInformation.setSysRoleId(roleUserId);
        authorityRoleMapper.insert(authorityRoleInformation);

        authorityRoleInformation.setId(null);
        authorityRoleInformation.setSysRoleId(roleCourierId);
        authorityRoleMapper.insert(authorityRoleInformation);
        this.save(authorityInformation);

    }

    @Override
    public Integer getUserCountByObject(String obj) {
        return authorityInformationMapper.getUserCountByObject(obj);
    }
}
