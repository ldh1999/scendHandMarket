package com.ldh.modules.api.GD.controller;

import com.ldh.api.GD.model.LocationResponse;
import com.ldh.api.GD.model.PositionNameResponse;
import com.ldh.modules.api.GD.service.LocationService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/gd/dg")
@Api(tags = "高德地址编码")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     *
     * @param address 根据名称获取经纬度
     * @return
     */
    @ApiOperation(value="根据名称获取经纬度", notes="根据名称获取经纬度")
    @RequestMapping(path = "getLocationByName", method = RequestMethod.GET)
    public Result<?> getLocationByName(@RequestParam(name = "address", required = true)String address){
        Result<Map> result = new Result<>();
        try {
            result.setResult(locationService.getLocationByName(address));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error("无法获取");
        }
        return result;
    }

    /**
     *
     * @param location 根据经纬度获取名称
     * @return
     */
    @ApiOperation(value="根据经纬度获取名称", notes="根据经纬度获取名称")
    @RequestMapping(path = "getNameByLocation", method = RequestMethod.GET)
    public Result<?> getNameByLocation(@RequestParam(name = "location", required = true)String location){
        Result<Map> result = new Result<>();
        try {
            result.setResult(locationService.getNameByLocation(location));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error("无法获取");
        }
        return result;
    }

}
