package com.ldh.modules.order.controller;

import User.AuthorityInformation;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.PaymentVO;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.rabbitmq.service.InventoryPreferencesRabbitMQService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import constant.OrderEnum;
import constant.UserOperationConstant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Vector;

@RestController
@Slf4j
@RequestMapping("order/payment")
public class PaymentController {

    @Autowired
    private OrderInformationService orderInformationService;

    @Autowired
    private InventoryPreferencesRabbitMQService inventoryPreferencesRabbitMQService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value="支付", notes="支付")
    @PostMapping(value = "/payment")
    public Result<?> paymentMoney(@RequestBody PaymentVO paymentVO){
        Result result = new Result();
        try{
            OrderInformation orderInformation = new OrderInformation();
            orderInformation
                    .setSts(OrderEnum.wait_accept.getOrder())
                    .setOrderId(paymentVO.getOrderId());
            //设置偏好
            orderInformationService.updateById(orderInformation);
            OrderInformation temp = orderInformationService.getById(paymentVO.getOrderId());
            inventoryPreferencesRabbitMQService.updatePreferences(
                    temp.getInventoryId(),
                    this.getUser().getAuthorityId(),
                    UserOperationConstant.BUY);
            result.succcess("支付成功");
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private AuthorityInformation getUser(){
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        return authorityInformation;
    }
}
