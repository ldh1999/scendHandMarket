package com.ldh.modules.order.mapper;

import java.util.List;

import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单物流
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */

@Mapper
public interface OrderPhysicalDistributionMapper extends BaseMapper<OrderPhysicalDistribution> {

}
