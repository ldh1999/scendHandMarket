package com.ldh.userService.client;

import com.ldh.userService.model.AuthorityInformationModel;
import com.ldh.userService.pojo.AuthorityInformation;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("userservice/user/handle")
public interface AuthorityClient {
    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<AuthorityInformationModel> selectById(@RequestParam(name = "id", required = true) String id);

    @RequestMapping(path = "selectByIds", method = RequestMethod.GET)
    Result<List<AuthorityInformationModel>> selectByIds(@RequestParam(name = "ids", required = true) String ids);

    @RequestMapping(path = "/selectByUserName", method = RequestMethod.GET)
    Result<AuthorityInformationModel> selectByUserName(@RequestParam(value = "username", required = true) String username);

    /** 用户管理列表 */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap AuthorityInformation authorityInformation,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);
    /** 用户管理添加 */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> insert(@RequestBody AuthorityInformation authorityInformation);

    /** 用户管理修改 */
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    Result<?> updateById(@RequestBody AuthorityInformation authorityInformation);

    /** 用户管理删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);
}