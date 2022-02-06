package com.ldh.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.service.ICourierService;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.mapper.OrderPhysicalDistributionMapper;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import constant.OrderEnum;
import constant.OrderPhysicalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 订单物流
 * @Author: ldh
 * @Date: 2022-01-08
 * @Version: V1.0
 */
@Service
public class OrderPhysicalDistributionServiceImpl extends ServiceImpl<OrderPhysicalDistributionMapper, OrderPhysicalDistribution> implements OrderPhysicalDistributionService {

    @Autowired
    private OrderPhysicalDistributionMapper orderPhysicalDistributionMapper;

    @Autowired
    private ICourierService courierService;

    @Autowired
    private OrderInformationService orderInformationService;

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

    @Override
    public Page<OrderPhysicalDistributionModel> listWork(Page page, QueryWrapper queryWrapper, OrderPhysicalDistributionVO orderPhysicalDistributionVO) {
        return orderPhysicalDistributionMapper.listWork(page, queryWrapper, orderPhysicalDistributionVO);
    }

    @Override
    @Transactional
    public void rightSended(String orderPhysicalDistributionId) {
        OrderPhysicalDistribution orderPhysicalDistribution = new OrderPhysicalDistribution();
        //更改物流状态
        orderPhysicalDistribution
                .setOrderPhysicalDistributionId(orderPhysicalDistributionId)
                .setSts(OrderPhysicalEnum.sended.getOrderPhysical());
        OrderPhysicalDistribution orderPhysicalDistribution1 = this.getById(orderPhysicalDistributionId);
        //更改订单状态
        OrderInformation orderInformation = new OrderInformation();
        orderInformation
                .setOrderId(orderPhysicalDistribution1.getOrderId())
                .setSts(OrderEnum.wait_accept_inventory.getOrder());
        orderInformationService.updateById(orderInformation);
        this.updateById(orderPhysicalDistribution);
    }

    @Override
    public Integer countOrderByObj(String object, String courierCode) {
        return orderPhysicalDistributionMapper.countOrderByObj(object, courierCode);
    }
}
