package com.ldh.orderService.client;

import com.ldh.orderService.pojo.PhysicalDistribution;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("orderservice/order/physicalDistribution/handle")
public interface PhysicalDistributionClient {

    /**
     * 快递员物流list
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    Result<?> list(@RequestParam(name = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId,
                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                              @RequestParam(name="column", required = false) String column,
                              @RequestParam(name="order", required = false) String order);

    /**
     * 快递员增加物流
     * @param physicalDistribution
     * @return
     */
    @PostMapping(value = "add")
    Result<?> add(@RequestBody PhysicalDistribution physicalDistribution);

    /**
     * 获取当前位置
     * @param orderPhysicalDistributionId
     * @return
     */
    @GetMapping(value = "getNowPositionBuOrderPhyId")
    Result<?> getNowPositionBuOrderPhyId(@RequestParam(name = "orderPhysicalDistributionId" , required = true)String orderPhysicalDistributionId);


}
