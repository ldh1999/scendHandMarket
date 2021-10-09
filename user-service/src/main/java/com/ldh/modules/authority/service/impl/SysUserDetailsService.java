package com.ldh.modules.authority.service.impl;

import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysUserEntity;
import com.ldh.modules.authority.mapper.AuthorityInformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorityInformationMapper authorityInformationMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUserEntity sysUserEntity = new SysUserEntity();
        if (StringUtils.isEmpty(s)){
            return null;
        }

        AuthorityInformation authorityInformation = authorityInformationMapper.findByUserName(s);
        if (authorityInformation == null){
            log.error("用户不存在");
            return null;
        }
        sysUserEntity.setUserId(authorityInformation.getAuthorityId());
        sysUserEntity.setUsername(authorityInformation.getAuthorityUsername());
        sysUserEntity.setPassword(authorityInformation.getAuthorityPassword());
        sysUserEntity.setSts(authorityInformation.getSts());

        return sysUserEntity;
    }
}
