package com.ldh.modules.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderMerchantInformationModel;
import com.ldh.modules.order.service.OrderInformationService;
import common.Result;
import common.StringTo;
import constant.OrderEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order/merchantClient")
@Slf4j
@RestController
public class OrderMerchantClientController {

    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 卖家订单分页列表查询
     *
     * @param orderInformation
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="卖家查看订单", notes="卖家查看订单")
    @GetMapping(value = "/merchantList")
    public Result<?> merchantList(OrderInformation orderInformation,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  @RequestParam(name="column", required = false) String column,
                                  @RequestParam(name="order", required = false) String order) {
        Page<OrderInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result result = new Result();
        try{
            IPage<OrderInformationModel> pageList = orderInformationService.listToMerchant(page, queryWrapper, orderInformation);
            result.setResult(pageList);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="商家接受订单", notes="商家接受订单")
    @GetMapping(value = "/acceptOrder")
    public Result<?> acceptOrder(@RequestParam(name = "id", required = true)String id) {
        Result<?> result = new Result<>();
        try{
            OrderInformation orderInformation = new OrderInformation();
            orderInformation
                    .setOrderId(id)
                    .setSts(OrderEnum.wait_send.getOrder());
            orderInformationService.updateById(orderInformation);
            result.succcess("已接受订单");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="查看订单详情", notes="查看订单详情")
    @GetMapping(value = "/orderDetail")
    public Result<?> orderDetail(@RequestParam(name = "id", required = true)String id) {
        Result<OrderMerchantInformationModel> result = new Result<>();
        try{
            result.setResult(orderInformationService.getByIdForMerchantDetail(id));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


}
