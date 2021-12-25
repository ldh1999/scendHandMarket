package com.ldh.sysService.client;

import com.ldh.sysService.pojo.SysDict;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("userservice/Sys/dict/handle")
public interface SysDictClient {
    /** 系统字典list */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap SysDict sysDict,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /** 添加字典 */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> add(@RequestBody SysDict sysDict);

    /** 字典删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);

     /** 字典修改 */
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    Result<?> updateById(@RequestBody SysDict sysDict);

    /** 字典查找 */
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    Result<?> selectById(@RequestParam(value = "id", required = true)String id);

    /**
     * 获取全部字典数据
     *
     * @return
     */
    @RequestMapping(value = "/queryAllDictItems", method = RequestMethod.GET)
    Result<?> queryAllDictItems();
}
