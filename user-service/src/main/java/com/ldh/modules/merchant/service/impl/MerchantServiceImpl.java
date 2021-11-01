package com.ldh.modules.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.mapper.MerchantMapper;
import com.ldh.modules.merchant.model.MerchantModel;
import com.ldh.modules.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public IPage<MerchantModel> list(Page page, QueryWrapper queryWrapper, Merchant merchant) {
        return merchantMapper.list(page, queryWrapper, merchant);
    }

    @Override
    public MerchantModel selectById(String id) {
        return merchantMapper.selectById(id);
    }

    @Override
    public MerchantModel selectByUserId(String userId) {
        return merchantMapper.selectByUserId(userId);
    }
}
