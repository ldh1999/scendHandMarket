package com.ldh.modules.user.service;

import com.ldh.userService.pojo.AuthorityRoleInformation;
import common.Result;
public interface AuthorityRoleService {

    Result<?> list(AuthorityRoleInformation authorityInformation, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> insert(AuthorityRoleInformation authorityRoleInformation);

    Result<?> deleteById(String id);

}
