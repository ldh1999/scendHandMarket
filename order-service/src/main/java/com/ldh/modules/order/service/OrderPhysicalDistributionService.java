package com.ldh.modules.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单物流
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */
public interface OrderPhysicalDistributionService extends IService<OrderPhysicalDistribution> {
    Page<OrderPhysicalDistributionModel> listAccept(Page page, QueryWrapper queryWrapper, OrderPhysicalDistributionVO orderPhysicalDistributionVO);

    void acceptCourier(String orderPhysicalDistributionId, String courierCode);
}
