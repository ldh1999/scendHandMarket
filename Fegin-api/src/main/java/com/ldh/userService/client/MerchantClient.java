package com.ldh.userService.client;

import com.ldh.userService.model.MerchantVO;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice/merchant/examine")
public interface MerchantClient {


    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<MerchantVO> selectById(@RequestParam(value = "id",required = true) String id);
}
