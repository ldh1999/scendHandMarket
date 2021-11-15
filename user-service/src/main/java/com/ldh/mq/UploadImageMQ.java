package com.ldh.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadImageMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void uploadImage(Object file){
        String exchangeName = "ldh.otherResource";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "image", file);
    }
}
