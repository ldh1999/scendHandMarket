package com.ldh.modules.authority.handle;

import com.ldh.modules.authority.entity.AuthorityAddress;
import com.ldh.modules.authority.service.AuthorityAddressService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags="用户地址对外接口")
@RestController
@RequestMapping("/UserAddress/handle")
public class AuthorityAddressHandle {

    @Autowired
    private AuthorityAddressService authorityAddressService;

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value="用户地址-通过id查询", notes="用户地址-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        AuthorityAddress authorityAddress = authorityAddressService.getById(id);
        return Result.OK(authorityAddress);
    }
}
