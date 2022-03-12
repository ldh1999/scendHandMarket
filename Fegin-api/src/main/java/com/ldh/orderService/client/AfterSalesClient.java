package com.ldh.orderService.client;

import com.ldh.orderService.model.AfterSalesModel;
import com.ldh.orderService.pojo.AfterSales;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("orderservice/order/afterSales/handle")
public interface AfterSalesClient {
    /**
     * 获取全部售后
     *
     * @param
     * @return
     */
    @GetMapping(value = "/getAllAfterSales")
    Result<?> getAllAfterSales(@SpringQueryMap AfterSales afterSales,
                                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                              @RequestParam(name = "column", required = false) String column,
                                              @RequestParam(name = "order", required = false) String order);

    /**
     * 获取详情信息
     * @param afterSalesId
     * @return
     */
    @GetMapping(value = "/getAfterSalesDetail")
    Result<?> getAfterSalesDetail(@RequestParam(name = "afterSalesId", required = true) String afterSalesId);
}
