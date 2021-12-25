package com.ldh.userService.client;

import com.ldh.userService.pojo.AuthorityRoleInformation;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice/User/UserRole/handle")
public interface AuthorityRoleInformationClient {

    @ApiOperation(value="用户权限列表", notes="用户权限列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap AuthorityRoleInformation authorityInformation,
                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                   @RequestParam(name="column", required = false) String column,
                   @RequestParam(name="order", required = false) String order);

    @ApiOperation(value="用户权限添加", notes="用户权限添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> insert(@RequestBody AuthorityRoleInformation authorityRoleInformation);

    @ApiOperation(value="用户权限删除", notes="用户权限删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);
}
