package com.ldh.modules.order.service.impl;

import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.mapper.OrderInformationMapper;
import com.ldh.modules.order.service.OrderInformationService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Service
public class OrderInformationServiceImpl extends ServiceImpl<OrderInformationMapper, OrderInformation> implements OrderInformationService {

}
