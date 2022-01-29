package com.ldh.modules.order.controller;

import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.PaymentVO;
import com.ldh.modules.order.service.OrderInformationService;
import common.Result;
import constant.OrderEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@Slf4j
@RequestMapping("order/payment")
public class PaymentController {

    @Autowired
    private OrderInformationService orderInformationService;


    @ApiOperation(value="支付", notes="支付")
    @PostMapping(value = "/payment")
    public Result<?> paymentMoney(@RequestBody PaymentVO paymentVO){
        Result result = new Result();
        try{
            OrderInformation orderInformation = new OrderInformation();
            orderInformation
                    .setSts(OrderEnum.wait_accept.getOrder())
                    .setOrderId(paymentVO.getOrderId());
            orderInformationService.updateById(orderInformation);
            result.succcess("支付成功");
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
