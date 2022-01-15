package com.ldh.modules.order.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import feign.QueryMap;
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

    Page<OrderPhysicalDistributionModel> listAccept(Page page, QueryWrapper queryWrapper, @Param("orderPhysicalDistributionVO") OrderPhysicalDistributionVO orderPhysicalDistribution);

    Page<OrderPhysicalDistributionModel> listWork(Page page, QueryWrapper queryWrapper, @Param("orderPhysicalDistributionVO") OrderPhysicalDistributionVO orderPhysicalDistribution);
}
