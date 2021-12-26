package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.orderService.client.OrderInformationClient;
import com.ldh.orderService.model.OrderInformationDetailModel;
import com.ldh.orderService.pojo.OrderInformation;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInformationServiceImpl implements OrderInformationService {

    @Autowired
    private OrderInformationClient orderInformationClient;

    @Override
    public Result<?> queryPageList(OrderInformation orderInformation, Integer pageNo, Integer pageSize, String column, String order) {
        return orderInformationClient.queryPageList(orderInformation, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> edit(OrderInformation orderInformation) {
        return orderInformationClient.edit(orderInformation);
    }

    @Override
    public Result<?> delete(String id) {
        return orderInformationClient.delete(id);
    }

    @Override
    public Result<OrderInformationDetailModel> queryByIdDetail(String id) {
        return orderInformationClient.queryByIdDetail(id);
    }
}
