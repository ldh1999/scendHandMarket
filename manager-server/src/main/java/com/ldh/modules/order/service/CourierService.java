package com.ldh.modules.order.service;

import com.ldh.orderService.pojo.Courier;
import common.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 快递员
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
public interface CourierService{

    /**
     * 分页列表查询
     *
     * @param courier
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result<?> queryPageList(Courier courier, Integer pageNo, Integer pageSize, String column, String order);

    /**
     * 添加
     *
     * @param courier
     * @return
     */
    Result<?> add(Courier courier);

    /**
     * 编辑
     *
     * @param courier
     * @return
     */
    Result<?> edit(Courier courier);

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    Result<?> delete(String id);

    Result<?> queryAllById(String code);
}
