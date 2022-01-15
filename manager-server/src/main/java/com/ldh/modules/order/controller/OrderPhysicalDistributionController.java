package com.ldh.modules.order.controller;

import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.orderService.pojo.OrderPhysicalDistribution;
import com.ldh.orderService.vo.OrderPhysicalDistributionVO;
import common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("order/orderPhysicalDistribution")
public class OrderPhysicalDistributionController {

    @Autowired
    private OrderPhysicalDistributionService orderPhysicalDistributionService;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('courier')")
    @ApiOperation(value="快递员接单-分页列表查询", notes="快递员接单-分页列表查询")
    @GetMapping(value = "/acceptList")
    public Result<?> acceptList(OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order){
        return orderPhysicalDistributionService.acceptList(orderPhysicalDistributionVO, pageNo, pageSize, column, order);
    }

    @ApiOperation(value="快递员接单列表", notes="快递员接单列表")
    @PreAuthorize("hasAuthority('courier')")
    @GetMapping(value = "/acceptCourier")
    public Result<?> acceptCourier(@RequestParam(value = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId){
        return orderPhysicalDistributionService.acceptCourier(orderPhysicalDistributionId);
    }


    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('courier')")
    @ApiOperation(value="快递员工作台-分页列表查询", notes="快递员工作台-分页列表查询")
    @GetMapping(value = "/listWork")
    public Result<?> listWork(OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order){
        return orderPhysicalDistributionService.listWork(orderPhysicalDistributionVO, pageNo, pageSize, column, order);
    }
}
