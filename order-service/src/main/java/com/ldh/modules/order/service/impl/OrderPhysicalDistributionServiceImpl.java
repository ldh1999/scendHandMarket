package com.ldh.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.service.ICourierService;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.mapper.OrderPhysicalDistributionMapper;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import constant.OrderPhysicalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 订单物流
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Service
public class OrderPhysicalDistributionServiceImpl extends ServiceImpl<OrderPhysicalDistributionMapper, OrderPhysicalDistribution> implements OrderPhysicalDistributionService {

    @Autowired
    private OrderPhysicalDistributionMapper orderPhysicalDistributionMapper;

    @Autowired
    private ICourierService courierService;

    @Override
    public Page<OrderPhysicalDistributionModel> listAccept(Page page, QueryWrapper queryWrapper, OrderPhysicalDistributionVO orderPhysicalDistributionVO) {
        return orderPhysicalDistributionMapper.listAccept(page, queryWrapper, orderPhysicalDistributionVO);
    }

    @Override
    @Transactional
    public void acceptCourier(String orderPhysicalDistributionId, String courierCode) {
        Courier courier = courierService.getByCode(courierCode);
        OrderPhysicalDistribution orderPhysicalDistribution = new OrderPhysicalDistribution();
        orderPhysicalDistribution
                .setOrderPhysicalDistributionId(orderPhysicalDistributionId)
                .setCourierId(courier.getCourierId())
                .setSts(OrderPhysicalEnum.sending.getOrderPhysical());
        this.updateById(orderPhysicalDistribution);
    }
}
