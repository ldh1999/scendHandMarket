package com.ldh.orderService.client;

import com.ldh.orderService.pojo.OrderPhysicalDistribution;
import com.ldh.orderService.vo.OrderPhysicalDistributionVO;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("orderservice/order/orderPhysicalDistribution/handle")
public interface OrderPhysicalDistributionClient {
    /**
     * 快递员接单列表
     * @param orderPhysicalDistributionVO
     * @param pageNo
     * @param pageSize
     * @param column
     * @param order
     * @return
     */
    @GetMapping(value = "/acceptList")
    Result<?> acceptList(@SpringQueryMap OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order);

    @GetMapping(value = "/acceptCourier")
    Result<?> acceptCourier(@RequestParam(value = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId,
                            @RequestParam(value = "courierCode", required = true) String courierCode);

    @GetMapping(value = "/listWork")
    Result<?> listWork(@SpringQueryMap OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                              @RequestParam(name="column", required = false) String column,
                              @RequestParam(name="order", required = false) String order);

    @GetMapping(value = "rightSended")
    Result<?> rightSended(@RequestParam(name = "orderPhysicalDistributionId" , required = true) String orderPhysicalDistributionId);


}
