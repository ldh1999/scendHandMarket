package com.ldh.listenter.user;

import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.service.AuthorityInformationService;
import constant.AmqpConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class SysUserListener {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = AmqpConstant.USER_INSERT_QUEUE),
            exchange = @Exchange(name = AmqpConstant.USER_EXCHANGE, type = ExchangeTypes.TOPIC)
    ))
    public void register(AuthorityInformation authorityInformation){
        authorityInformationService.registerCourier(authorityInformation);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = AmqpConstant.USER_DELETE_QUEUE),
            exchange = @Exchange(name = AmqpConstant.USER_EXCHANGE, type = ExchangeTypes.TOPIC)
    ))
    public void deleteByUsername(String username){
        authorityInformationService.deleteAnyOneById(authorityInformationService.findByUserName(username,null).getAuthorityId());
    }


}
