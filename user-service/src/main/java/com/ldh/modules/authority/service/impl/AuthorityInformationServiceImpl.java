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
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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
        if (!StringUtils.isEmpty(authorityInformationModel.getImgPath())){
            authorityInformationModel.setImgPath(this.getNowUrl(request)+authorityInformationModel.getImgPath());
        }
        return authorityInformationModel;
    }

    @Override
    public void register(AuthorityInformation authorityInformation) {

        Random random = new Random();
        String userId = UUID.randomUUID().toString();
        String authorityName = "游客:"+random.nextInt();
        String roleId = sysRoleMapper.selectByRoleNo("user").getId();
        authorityInformation.setAuthorityId(userId);
        authorityInformation.setAuthorityName(authorityName);
        AuthorityRoleInformation authorityRoleInformation= new AuthorityRoleInformation();
        authorityRoleInformation.setAuthorityId(userId);
        authorityRoleInformation.setSysRoleId(roleId);

        this.save(authorityInformation);
        authorityRoleMapper.insert(authorityRoleInformation);

    }

    private String getNowUrl(ServletRequest request){
        return request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort();
    }
}
