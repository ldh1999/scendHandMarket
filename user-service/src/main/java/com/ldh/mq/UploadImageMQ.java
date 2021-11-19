package com.ldh.mq;

import constant.MqName;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadImageMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void uploadImage(Object object,Class<?> clazz){
        if (clazz == null){
            throw new RuntimeException("this class is not null");
        }
        else if(clazz != null){
            rabbitTemplate.convertAndSend(MqName.UPLOAD_IMAGE_EXCHANGE, MqName.UPLOAD_IMAGE_QUEUE_KEY, clazz.cast(object));
        }
    }
}
