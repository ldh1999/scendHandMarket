package com.ldh.modules.order.controller;

import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.orderService.pojo.OrderInformation;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "订单信息表对外接口")
@RestController
@RequestMapping("/order/orderInformation")
public class OrderInformationController {

    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 分页列表查询
     *
     * @param orderInformation
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "订单信息表-分页列表查询", notes = "订单信息表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(OrderInformation orderInformation,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "column", required = false) String column,
                                   @RequestParam(name = "order", required = false) String order) {
       
        return orderInformationService.queryPageList(orderInformation, pageNo, pageSize, column, order);
    }
    
    /**
     * 编辑
     *
     * @param orderInformation
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "订单信息表-编辑", notes = "订单信息表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody OrderInformation orderInformation) {
        return orderInformationService.edit(orderInformation);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "订单信息表-通过id删除", notes = "订单信息表-通过id删除")
    @DeleteMapping(value = "/deleteById")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        
        return orderInformationService.delete(id);
    }
    /**
     * 通过id查询详情
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "订单信息表-通过id查询详情", notes = "订单信息表-通过id查询详情")
    @GetMapping(value = "/queryByIdDetail")
    public Result<?> queryByIdDetail(@RequestParam(name = "id", required = true) String id) {
       
        return orderInformationService.queryByIdDetail(id);
    }
    
}
