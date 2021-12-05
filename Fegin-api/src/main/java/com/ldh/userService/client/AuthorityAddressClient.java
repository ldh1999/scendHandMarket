package com.ldh.userService.client;

import com.ldh.userService.model.AuthorityAddressModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice/UserAddress/handle")
public interface AuthorityAddressClient {
    @GetMapping(value = "/queryById")
    Result<AuthorityAddressModel> queryById(@RequestParam(name="id",required=true) String id);
}
