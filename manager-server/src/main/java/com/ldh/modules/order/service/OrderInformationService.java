package com.ldh.modules.order.service;

import com.ldh.orderService.model.OrderInformationDetailModel;
import com.ldh.orderService.pojo.OrderInformation;
import common.Result;

public interface OrderInformationService {

    Result<?> queryPageList(OrderInformation orderInformation, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> edit(OrderInformation orderInformation);

    Result<?> delete(String id);

    Result<OrderInformationDetailModel> queryByIdDetail(String id);
}
