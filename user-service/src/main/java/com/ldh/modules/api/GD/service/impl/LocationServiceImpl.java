package com.ldh.modules.api.GD.service.impl;

import com.ldh.api.GD.client.LocationClient;
import com.ldh.api.GD.model.LocationResponse;
import com.ldh.api.GD.model.PositionNameResponse;
import com.ldh.modules.api.GD.service.LocationService;
import key.GaoDe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationClient locationClient;

    @Override
    public Map getLocationByName(String address) {
        return locationClient.getLocationByName(GaoDe.key, address);
    }

    @Override
    public Map getNameByLocation(String location) {
        return locationClient.getNameByLocation(GaoDe.key,location, null);
    }
}
