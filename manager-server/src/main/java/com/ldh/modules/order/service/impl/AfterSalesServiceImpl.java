package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.orderService.client.AfterSalesClient;
import com.ldh.orderService.model.AfterSalesModel;
import com.ldh.orderService.pojo.AfterSales;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfterSalesServiceImpl implements AfterSalesService {

    @Autowired
    private AfterSalesClient afterSalesClient;

    @Override
    public Result<?> getAllAfterSales(AfterSales afterSales, Integer pageNo, Integer pageSize, String column, String order) {
        return afterSalesClient.getAllAfterSales(afterSales, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> getAfterSalesDetail(String afterSalesId) {
        return afterSalesClient.getAfterSalesDetail(afterSalesId);
    }
}
