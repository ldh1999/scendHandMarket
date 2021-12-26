package com.ldh.modules.user.service.impl;

import com.ldh.modules.user.service.AuthorityInformationService;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.pojo.AuthorityInformation;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityInformationServiceImpl implements AuthorityInformationService {

    @Autowired
    private AuthorityClient authorityClient;

    @Override
    public Result<?> list(AuthorityInformation authorityInformation, Integer pageNo, Integer pageSize, String column, String order) {
        return authorityClient.list(authorityInformation, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> insert(AuthorityInformation authorityInformation) {
        return authorityClient.insert(authorityInformation);

    }

    @Override
    public Result<?> updateById(AuthorityInformation authorityInformation) {
        return authorityClient.updateById(authorityInformation);

    }

    @Override
    public Result<?> deleteById(String id) {
        return authorityClient.deleteById(id);

    }

    @Override
    public Result<?> selectById(String id) {
        return authorityClient.selectById(id);

    }
}
