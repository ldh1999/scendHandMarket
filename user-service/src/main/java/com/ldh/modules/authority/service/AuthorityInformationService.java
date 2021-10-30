package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;

public interface AuthorityInformationService extends IService<AuthorityInformation> {
    IPage<AuthorityInformationModel> list(AuthorityInformation authorityInformation, Page page, QueryWrapper queryWrapper);

    int countUserName(AuthorityInformation authorityInformation);

    AuthorityInformationModel findByUserName(String username);

    void register(AuthorityInformation authorityInformation);
}
