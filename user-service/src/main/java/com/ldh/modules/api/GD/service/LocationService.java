package com.ldh.modules.api.GD.service;

import com.ldh.api.GD.model.LocationResponse;
import com.ldh.api.GD.model.PositionNameResponse;

import java.util.Map;

public interface LocationService {

    /**
     *
     * @param address 位置名称
     * @return
     */
    Map getLocationByName(String address);

    /**
     *
     * @param location 位置经纬度
     * @return
     */
    Map getNameByLocation(String location);

}
