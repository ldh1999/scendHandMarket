package com.ldh.modules.inventory.service;

import com.ldh.inventoryService.pojo.Merchant;
import common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MerchantService {

    Result<?> list(Merchant merchant, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> updateById(Merchant merchant);

    Result<?> selectById(String id);
}
