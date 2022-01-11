package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.CourierServicesCompanyService;
import com.ldh.orderService.client.CourierServicesCompanyClient;
import com.ldh.orderService.pojo.CourierServicesCompany;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServicesCompanyServiceImpl implements CourierServicesCompanyService {

    @Autowired
    private CourierServicesCompanyClient courierServicesCompanyClient;

    @Override
    public Result<?> queryPageList(CourierServicesCompany courierServicesCompany, Integer pageNo, Integer pageSize, String column, String order) {
        return courierServicesCompanyClient.queryPageList(courierServicesCompany, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> add(CourierServicesCompany courierServicesCompany) {
        return courierServicesCompanyClient.add(courierServicesCompany);
    }

    @Override
    public Result<?> edit(CourierServicesCompany courierServicesCompany) {
        return courierServicesCompanyClient.edit(courierServicesCompany);
    }

    @Override
    public Result<?> delete(String id) {
        return courierServicesCompanyClient.delete(id);
    }

    @Override
    public Result<?> queryById(String id) {
        return courierServicesCompanyClient.queryById(id);
    }
}
