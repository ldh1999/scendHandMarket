package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.CourierService;
import com.ldh.orderService.client.CourierClient;
import com.ldh.orderService.pojo.Courier;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierClient courierClient;

    @Override
    public Result<?> queryPageList(Courier courier, Integer pageNo, Integer pageSize, String column, String order) {
        return courierClient.queryPageList(courier, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> add(Courier courier) {
        return courierClient.add(courier);
    }

    @Override
    public Result<?> edit(Courier courier) {
        return courierClient.edit(courier);
    }

    @Override
    public Result<?> delete(String id) {
        return courierClient.delete(id);
    }

    @Override
    public Result<?> queryAllById(String code) {
        return courierClient.queryAllByCode(code);
    }
}
