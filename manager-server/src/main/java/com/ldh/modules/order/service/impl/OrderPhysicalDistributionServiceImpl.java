package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.orderService.client.CourierClient;
import com.ldh.orderService.client.OrderPhysicalDistributionClient;
import com.ldh.orderService.pojo.OrderPhysicalDistribution;
import com.ldh.orderService.vo.OrderPhysicalDistributionVO;
import com.ldh.security.entity.SysUserEntity;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderPhysicalDistributionServiceImpl implements OrderPhysicalDistributionService {

    @Autowired
    private OrderPhysicalDistributionClient orderPhysicalDistributionClient;

    @Autowired
    private CourierClient courierClient;

    @Override
    public Result<?> acceptList(OrderPhysicalDistributionVO orderPhysicalDistributionVO, Integer pageNo, Integer pageSize, String column, String order) {
        SysUserEntity sysUserEntity = (SysUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderPhysicalDistributionVO.setCourierUserId(sysUserEntity.getUserId());
        orderPhysicalDistributionVO.setCourierUsername(sysUserEntity.getUsername());
        return orderPhysicalDistributionClient.acceptList(orderPhysicalDistributionVO, pageNo, pageSize, column, order);
    }
}
