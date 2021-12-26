package com.ldh.modules.user.service;

import com.ldh.userService.pojo.AuthorityInformation;
import common.Result;

public interface AuthorityInformationService {

    Result<?> list(AuthorityInformation authorityInformation, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> insert(AuthorityInformation authorityInformation);

    Result<?> updateById(AuthorityInformation authorityInformation);

    Result<?> deleteById(String id);

    Result<?> selectById(String id);
}
