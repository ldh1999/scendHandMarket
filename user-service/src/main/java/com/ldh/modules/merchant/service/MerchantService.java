package com.ldh.modules.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantModel;

public interface MerchantService extends IService<Merchant> {
    IPage<MerchantModel> list(Page page, QueryWrapper queryWrapper, Merchant merchant);

    MerchantModel selectById(String id);

    MerchantModel selectByUserId(String userId);

}
