package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.PhysicalDistributionService;
import com.ldh.orderService.client.PhysicalDistributionClient;
import com.ldh.orderService.pojo.PhysicalDistribution;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalDistributionServiceImpl implements PhysicalDistributionService {

    @Autowired
    private PhysicalDistributionClient physicalDistributionClient;

    @Override
    public Result<?> list(String orderPhysicalDistributionId, Integer pageNo, Integer pageSize, String column, String order) {
        return physicalDistributionClient.list(orderPhysicalDistributionId, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> add(PhysicalDistribution physicalDistribution) {
        return physicalDistributionClient.add(physicalDistribution);
    }

    @Override
    public Result<?> getNowPositionBuOrderPhyId(String orderPhysicalDistributionId) {
        return physicalDistributionClient.getNowPositionBuOrderPhyId(orderPhysicalDistributionId);
    }
}
