package com.ldh.inventoryService.client;

import com.ldh.inventoryService.model.MerchantModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/merchant/examine")
public interface MerchantClient {
    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<MerchantModel> selectById(@RequestParam(value = "id",required = true) String id);
}
