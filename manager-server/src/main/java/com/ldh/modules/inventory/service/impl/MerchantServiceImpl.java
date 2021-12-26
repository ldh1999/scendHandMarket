package com.ldh.modules.inventory.service.impl;

import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.inventoryService.pojo.Merchant;
import com.ldh.modules.inventory.service.MerchantService;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantClient merchantClient;

    @Override
    public Result<?> list(Merchant merchant, Integer pageNo, Integer pageSize, String column, String order) {
        return merchantClient.list(merchant, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> updateById(Merchant merchant) {
        return merchantClient.updateById(merchant);
    }

    @Override
    public Result<?> selectById(String id) {
        return merchantClient.selectById(id);

    }
}
