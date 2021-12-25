package com.ldh.modules.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationDetailModel;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderMerchantInformationModel;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-11-28
 * @Version: V1.0
 */
public interface OrderInformationService extends IService<OrderInformation> {

    Page<OrderInformationModel> list(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation) throws Exception;

    Page<OrderInformationModel> listToBuyer(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation);

    Page<OrderInformationModel> listToMerchant(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation) throws Exception;

    OrderMerchantInformationModel getByIdForMerchantDetail(String id);

    OrderInformationDetailModel getByIdDetail(String id) throws Exception;
}
