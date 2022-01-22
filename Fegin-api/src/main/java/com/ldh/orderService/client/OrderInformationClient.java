package com.ldh.orderService.client;

import com.ldh.orderService.model.OrderInformationDetailModel;
import com.ldh.orderService.pojo.OrderInformation;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient("orderservice/order/orderInformation/handle")
public interface OrderInformationClient {

    @GetMapping(value = "/list")
    Result<?> queryPageList(@SpringQueryMap OrderInformation orderInformation,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "column", required = false) String column,
                            @RequestParam(name = "order", required = false) String order);

    @PutMapping(value = "/edit")
    Result<?> edit(@RequestBody OrderInformation orderInformation);

    @DeleteMapping(value = "/deleteById")
    Result<?> delete(@RequestParam(name = "id", required = true) String id);

    @GetMapping(value = "/queryByIdDetail")
    Result<OrderInformationDetailModel> queryByIdDetail(@RequestParam(name = "id", required = true) String id);

    @GetMapping(value = "/getOrderCountByObject")
    Result<?> getOrderCountByObject(@RequestParam(name = "id", required = false) String obj);
}
