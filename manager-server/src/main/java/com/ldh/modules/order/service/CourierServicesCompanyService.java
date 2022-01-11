package com.ldh.modules.order.service;

import com.ldh.orderService.pojo.CourierServicesCompany;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

public interface CourierServicesCompanyService {
    /**
     * 分页列表查询
     *
     * @param courierServicesCompany
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result<?> queryPageList(CourierServicesCompany courierServicesCompany, Integer pageNo, Integer pageSize, String column, String order);

    /**
     * 添加
     *
     * @param courierServicesCompany
     * @return
     */
    Result<?> add(@RequestBody CourierServicesCompany courierServicesCompany);

    /**
     * 编辑
     *
     * @param courierServicesCompany
     * @return
     */
    Result<?> edit(@RequestBody CourierServicesCompany courierServicesCompany);

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    Result<?> delete(@RequestParam(name="id",required=true) String id);
    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Result<?> queryById(@RequestParam(name="id",required=true) String id);
}
