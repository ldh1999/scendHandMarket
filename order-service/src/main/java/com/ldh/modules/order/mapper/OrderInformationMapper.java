package com.ldh.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.order.entity.OrderInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Mapper
public interface OrderInformationMapper extends BaseMapper<OrderInformation> {

}
