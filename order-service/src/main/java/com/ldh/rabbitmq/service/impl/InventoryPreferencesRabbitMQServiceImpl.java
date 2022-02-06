package com.ldh.rabbitmq.service.impl;

import com.ldh.rabbitmq.service.InventoryPreferencesRabbitMQService;
import constant.AmqpConstant;
import constant.UserOperationConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class InventoryPreferencesRabbitMQServiceImpl implements InventoryPreferencesRabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Override
    public void updatePreferences(String inventoryId, String userId, String operation) {
        Map<String, String> map = new HashMap();
        map.put("inventoryId", inventoryId);
        map.put("userId", userId);
        map.put("operation", operation);
        rabbitTemplate.convertAndSend(AmqpConstant.PREFERENCES_EXCHANGE, "preferences.insert", map);
    }
}
