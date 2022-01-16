package com.ldh.modules.order.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.PhysicalDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description: 订单信息表
 * @Author: ldh
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Mapper
public interface OrderInformationMapper extends BaseMapper<OrderInformation> {

    Page<OrderInformationModel> list(Page page, QueryWrapper queryWrapper,@Param("orderInformation") OrderInformation orderInformation);

    PhysicalDetailModel getPhyDetail(String orderId);
}
