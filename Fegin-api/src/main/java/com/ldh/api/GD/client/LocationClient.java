package com.ldh.api.GD.client;

import com.ldh.api.GD.model.LocationResponse;
import com.ldh.api.GD.model.PositionNameResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(url = "https://restapi.amap.com/v3/geocode",value = "test")
public interface LocationClient {

    /**
     *
     * @param key 高德key
     * @param address 位置名称
     * @return
     */
    @RequestMapping(path = "geo", method = RequestMethod.GET)
    Map getLocationByName(@RequestParam(name = "key") String key,
                                       @RequestParam(name = "address")String address);

    /**
     *
     * @param key 高德key
     * @param location 位置经纬度
     * @param homeorcorp 默认为1，homeorcorp 参数的设置可以影响召回 POI 内容的排序策略，目前提供三个可选参数：
     *
     * 0：不对召回的排序策略进行干扰。
     *
     * 1：综合大数据分析将居家相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。
     *
     * 2：综合大数据分析将公司相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。
     * @return
     */
    @RequestMapping(path = "regeo", method = RequestMethod.GET)
    Map getNameByLocation(@RequestParam(name = "key") String key,
                          @RequestParam(name = "location")String location,
                          @RequestParam(name = "homeorcorp",defaultValue = "1") String homeorcorp);


}
