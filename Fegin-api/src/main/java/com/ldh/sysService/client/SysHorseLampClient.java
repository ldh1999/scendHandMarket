package com.ldh.sysService.client;

import com.ldh.sysService.model.SysHorseLampVO;
import com.ldh.sysService.pojo.SysHorseLamp;
import common.Result;
import common.StringTo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("userservice/sys/horseLamp/handle")
public interface SysHorseLampClient {

    /**
     * 走马灯列表
     * @param sysHorseLamp
     * @param pageNo
     * @param pageSize
     * @param column
     * @param order
     * @return
     */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap SysHorseLamp sysHorseLamp,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /**
     * 主页走马灯
     * @return
     */
    @RequestMapping(path = "/homeListClient", method = RequestMethod.GET)
    Result<?> homeListClient();

    /**
     * 添加走马灯
     * @param sysHorseLampVO
     * @return
     */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> add(@RequestBody SysHorseLampVO sysHorseLampVO);

    /**
     * 走马灯删除
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);

    /**
     * 走马灯修改
     * @param sysHorseLamp
     * @return
     */
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    Result<?> updateById(@RequestBody SysHorseLamp sysHorseLamp);


}
