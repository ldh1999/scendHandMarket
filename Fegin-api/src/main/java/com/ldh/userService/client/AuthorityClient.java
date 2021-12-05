package com.ldh.userService.client;

import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("userservice/user/handle")
public interface AuthorityClient {
    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<AuthorityInformationModel> selectById(@RequestParam(name = "id", required = true) String id);

    @RequestMapping(path = "selectByIds", method = RequestMethod.GET)
    Result<List<AuthorityInformationModel>> selectByIds(@RequestParam("ids") String ids);
}
