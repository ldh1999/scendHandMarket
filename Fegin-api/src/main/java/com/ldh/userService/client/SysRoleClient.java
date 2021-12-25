package com.ldh.userService.client;

import com.ldh.userService.pojo.SysRole;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice/sys/role/handle")
public interface SysRoleClient {

    @RequestMapping(path = "list",method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap SysRole sysRole,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    @RequestMapping(path = "delete" , method = RequestMethod.DELETE)
    Result<?> delete(@RequestParam(name="id")String id);

    @RequestMapping(path = "add" , method = RequestMethod.POST)
    Result<?> add(@RequestBody SysRole sysRole);

    @RequestMapping(path = "updateById" , method = RequestMethod.POST)
    Result<?> update(@RequestBody SysRole sysRole);

}
