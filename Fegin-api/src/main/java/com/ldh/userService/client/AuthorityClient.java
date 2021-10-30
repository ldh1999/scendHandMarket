package com.ldh.userService.client;

import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice/User/AuthorityInformation")
public interface AuthorityClient {
    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<AuthorityInformationModel> selectById(@RequestParam(name = "id", required = true) String id);

}
