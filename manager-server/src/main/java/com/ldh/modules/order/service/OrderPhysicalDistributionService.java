package com.ldh.modules.order.service;

import com.ldh.orderService.vo.OrderPhysicalDistributionVO;
import common.Result;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderPhysicalDistributionService {
    Result<?> acceptList(OrderPhysicalDistributionVO orderPhysicalDistributionVO, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> acceptCourier(String orderPhysicalDistributionId);

    @GetMapping(value = "/listWork")
    Result<?> listWork(OrderPhysicalDistributionVO orderPhysicalDistributionVO, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> rightSended(@RequestParam(name = "orderPhysicalDistributionId" , required = true) String orderPhysicalDistributionId);

}
