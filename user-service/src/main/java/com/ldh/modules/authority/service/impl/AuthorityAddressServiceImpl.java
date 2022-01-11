package com.ldh.modules.authority.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityAddress;
import com.ldh.modules.authority.mapper.AuthorityAddressMapper;
import com.ldh.modules.authority.model.AuthorityAddressModel;
import com.ldh.modules.authority.service.AuthorityAddressService;
import common.OptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 用户地址
 * @Author: ldh
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Service
public class AuthorityAddressServiceImpl extends ServiceImpl<AuthorityAddressMapper, AuthorityAddress> implements AuthorityAddressService {

    @Autowired
    private AuthorityAddressMapper authorityAddressMapper;

    @Override
    public Page<AuthorityAddressModel> getListClient(Page page, QueryWrapper queryWrapper, AuthorityAddress authorityAddress) {
        return authorityAddressMapper.getListClient(page, queryWrapper, authorityAddress);
    }

    @Override
    public List<OptionModel> getOptionByUserId(String id) {
        return authorityAddressMapper.getOptionByUserId(id);
    }
}
