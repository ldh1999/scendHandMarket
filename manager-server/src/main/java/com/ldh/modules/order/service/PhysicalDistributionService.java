package com.ldh.modules.order.service;

import com.ldh.orderService.pojo.PhysicalDistribution;
import common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PhysicalDistributionService {
    Result<?> list(String orderPhysicalDistributionId, Integer pageNo, Integer pageSize, String column, String order);

    /**
     * 快递员增加物流
     * @param physicalDistribution
     * @return
     */
    Result<?> add(PhysicalDistribution physicalDistribution);

    Result<?> getNowPositionBuOrderPhyId(String orderPhysicalDistributionId);

}
