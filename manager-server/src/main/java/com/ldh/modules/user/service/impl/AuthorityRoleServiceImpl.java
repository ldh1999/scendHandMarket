package com.ldh.modules.user.service.impl;

import com.ldh.modules.user.service.AuthorityRoleService;
import com.ldh.userService.client.AuthorityRoleInformationClient;
import com.ldh.userService.pojo.AuthorityRoleInformation;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityRoleServiceImpl implements AuthorityRoleService {

    @Autowired
    private AuthorityRoleInformationClient authorityRoleInformationClient;


    @Override
    public Result<?> list(AuthorityRoleInformation authorityInformation, Integer pageNo, Integer pageSize, String column, String order) {
        return authorityRoleInformationClient.list(authorityInformation, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> insert(AuthorityRoleInformation authorityRoleInformation) {
        return authorityRoleInformationClient.insert(authorityRoleInformation);

    }

    @Override
    public Result<?> deleteById(String id) {
        return authorityRoleInformationClient.deleteById(id);

    }
}
