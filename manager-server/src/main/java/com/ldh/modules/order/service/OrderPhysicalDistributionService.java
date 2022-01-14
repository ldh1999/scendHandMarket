package com.ldh.modules.order.service;

import com.ldh.orderService.vo.OrderPhysicalDistributionVO;
import common.Result;

public interface OrderPhysicalDistributionService {
    Result<?> acceptList(OrderPhysicalDistributionVO orderPhysicalDistributionVO, Integer pageNo, Integer pageSize, String column, String order);
}
