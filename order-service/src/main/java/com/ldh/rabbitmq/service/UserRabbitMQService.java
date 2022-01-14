package com.ldh.rabbitmq.service;

import com.ldh.modules.informationMaintenance.entity.Courier;

public interface UserRabbitMQService {

    void register(Courier courier);

    void deleteByUsername(String username);
}
