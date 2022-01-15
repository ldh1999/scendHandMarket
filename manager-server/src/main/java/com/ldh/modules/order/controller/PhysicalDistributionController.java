package com.ldh.modules.order.controller;

import com.ldh.modules.order.service.PhysicalDistributionService;
import com.ldh.orderService.pojo.PhysicalDistribution;
import common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("order/physicalDistribution")
public class PhysicalDistributionController {

    @Autowired
    private PhysicalDistributionService physicalDistributionService;

    /**
     * 快递员物流list
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('courier')")
    @GetMapping(value = "/list")
    public Result<?> list(@RequestParam(name = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId,
                       @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                       @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                       @RequestParam(name="column", required = false) String column,
                       @RequestParam(name="order", required = false) String order){
        return physicalDistributionService.list(orderPhysicalDistributionId, pageNo, pageSize, column, order);
    }

    /**
     * 快递员增加物流
     * @param physicalDistribution
     * @return
     */
    @PreAuthorize("hasAuthority('courier')")
    @PostMapping(value = "add")
    public Result<?> add(@RequestBody PhysicalDistribution physicalDistribution){
        return physicalDistributionService.add(physicalDistribution);
    }

    /**
     *获取物流最新位置
     * @param orderPhysicalDistributionId
     * @return
     */
    @PreAuthorize("hasAuthority('courier')")
    @GetMapping(value = "getNowPositionBuOrderPhyId")
    public Result<?> getNowPositionBuOrderPhyId(@RequestParam(name = "orderPhysicalDistributionId" , required = true)String orderPhysicalDistributionId){
        return physicalDistributionService.getNowPositionBuOrderPhyId(orderPhysicalDistributionId);
    }
}
