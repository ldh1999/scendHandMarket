package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthorityInformationService extends IService<AuthorityInformation> {
    IPage<AuthorityInformationModel> list(AuthorityInformation authorityInformation, Page page, QueryWrapper queryWrapper);

    int countUserName(AuthorityInformation authorityInformation);

    AuthorityInformationModel findByUserName(String username,  HttpServletRequest request);

    /**
     * 普通用户注册
     * @param authorityInformation
     */
    void register(AuthorityInformation authorityInformation);

    List<AuthorityInformationModel> selectByIds(String[] ids);

    void deleteAnyOneById(String id);

    /**
     * 快递员注册
     * @param authorityInformation
     */
    void registerCourier(AuthorityInformation authorityInformation);

    Integer getUserCountByObject(String obj);
}
