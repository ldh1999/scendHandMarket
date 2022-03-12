package com.ldh.modules.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.orderService.model.AfterSalesModel;
import com.ldh.orderService.pojo.AfterSales;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 售后
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="售后")
@RestController
@RequestMapping("/order/afterSales")
public class AfterSalesController {

    @Autowired
    private AfterSalesService afterSalesService;

    /**
     * 获取全部售后
     *
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="售后-获取全部售后", notes="售后-获取全部售后")
    @GetMapping(value = "/getAllAfterSales")
    public Result<?> getAllAfterSales(AfterSales afterSales,
                                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                     @RequestParam(name = "column", required = false) String column,
                                                     @RequestParam(name = "order", required = false) String order){

        return afterSalesService.getAllAfterSales(afterSales, pageNo, pageSize, column, order);
    }

    /**
     * 获取详情信息
     * @param afterSalesId
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/getAfterSalesDetail")
    Result<?> getAfterSalesDetail(@RequestParam(name = "afterSalesId", required = true) String afterSalesId){
        return afterSalesService.getAfterSalesDetail(afterSalesId);
    }

}
