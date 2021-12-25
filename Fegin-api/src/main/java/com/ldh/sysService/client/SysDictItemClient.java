package com.ldh.sysService.client;

import com.ldh.sysService.pojo.SysDictItem;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("userservice/Sys/dictItem/handle")
public interface SysDictItemClient {

    /** 字典项列表 */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap SysDictItem sysDictItem,
                          @RequestParam(name = "dictId") String dictId,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /** 字典项增加 */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> insert(@RequestBody SysDictItem sysDictItem);

    /** 字典项删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);

    /** 根据字典项和字典表查值 */
    @RequestMapping(path = "/getDictItem", method = RequestMethod.GET)
    Result<?> getItemValueBydictNoAndItemKey(@RequestParam(value = "dictNo", required = true)String dictNo,
                                                    @RequestParam(value = "itemKey", required = true)String itemKey);
    /** 跟胡字典编码获取字典项item */
    @RequestMapping(path = "/getOptionByDictNo", method = RequestMethod.GET)
    Result<?> getOptionByDictNo(@RequestParam(value = "dictNo", required = true)String dictNo);
}
