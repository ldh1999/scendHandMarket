package com.ldh.modules.order.service;

import com.ldh.orderService.model.AfterSalesModel;
import com.ldh.orderService.pojo.AfterSales;
import common.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface AfterSalesService {
    /**
     * 获取全部售后
     *
     * @param
     * @return
     */
    Result<?> getAllAfterSales(AfterSales afterSales, Integer pageNo, Integer pageSize, String column, String order);

    /**
     * 获取详情信息
     * @param afterSalesId
     * @return
     */
    Result<?> getAfterSalesDetail(String afterSalesId);
}
