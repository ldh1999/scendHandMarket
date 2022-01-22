package com.ldh.modules.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationDetailModel;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderMerchantInformationModel;
import com.ldh.modules.order.model.PhysicalDetailModel;
import com.ldh.modules.order.vo.SendInventoryVO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单信息表
 * @Author: ldh
 * @Date:   2021-11-28
 * @Version: V1.0
 */
public interface OrderInformationService extends IService<OrderInformation> {

    Page<OrderInformationModel> list(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation) throws Exception;

    Page<OrderInformationModel> listToBuyer(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation);

    Page<OrderInformationModel> listToMerchant(Page page, QueryWrapper queryWrapper, @Param("orderInformation") OrderInformation orderInformation) throws Exception;

    OrderMerchantInformationModel getByIdForMerchantDetail(String id);

    OrderInformationDetailModel getByIdDetail(String id) throws Exception;

    /** 商家发货 */
    void sendInventory(SendInventoryVO sendInventoryVO);

    /** 订单结束（确认收货） */
    void orderEnd(String id);

    /** 根据订单id获取物流信息 */
    PhysicalDetailModel getPhysicalDetail(String orderId);

    /** 根据条件获取order数量 */
    Integer getOrderCountByObject(String obj);
}
