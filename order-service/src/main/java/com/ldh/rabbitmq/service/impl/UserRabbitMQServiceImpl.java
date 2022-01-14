package com.ldh.rabbitmq.service.impl;

import User.AuthorityInformation;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.rabbitmq.service.UserRabbitMQService;
import constant.AmqpConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserRabbitMQServiceImpl implements UserRabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Override
    public void register(Courier courier) {
        AuthorityInformation authorityInformation = new AuthorityInformation();
        authorityInformation.setAuthorityName("快递员"+UUID.randomUUID().toString());
        authorityInformation.setAuthorityUsername(courier.getCourierCode());
        authorityInformation.setAuthorityPassword("123456");
        authorityInformation.setRealName(courier.getCourierName());
        authorityInformation.setPhone(courier.getCourierPhone());
        authorityInformation.setRemark(courier.getRemark());
        rabbitTemplate.convertAndSend(AmqpConstant.USER_EXCHANGE, "user.insert",authorityInformation);
    }

    @Override
    public void deleteByUsername(String username) {
        rabbitTemplate.convertAndSend(AmqpConstant.USER_EXCHANGE, "user.delete",username);
    }
}
