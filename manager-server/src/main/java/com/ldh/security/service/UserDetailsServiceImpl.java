package com.ldh.security.service;

import com.ldh.security.entity.SysUserEntity;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthorityClient authorityClient;

    @Autowired
    private PasswordEncoder pe;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Result<AuthorityInformationModel> result = null;
        try{
            result = authorityClient.selectByUserName(username);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        AuthorityInformationModel authorityInformationModel = result.getResult();
        if(!result.isSuccess() || authorityInformationModel == null){
            throw new UsernameNotFoundException("username not find");
        }
//        String token = UUID.randomUUID().toString();
        return new SysUserEntity(authorityInformationModel.getAuthorityId(),
                "",
                authorityInformationModel.getAuthorityUsername(),
                pe.encode(authorityInformationModel.getAuthorityPassword()),
                authorityInformationModel.getSts(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorityInformationModel.getRoleNos()));
    }
}
