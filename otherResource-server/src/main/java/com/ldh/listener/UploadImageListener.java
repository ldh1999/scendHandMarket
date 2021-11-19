package com.ldh.listener;

import com.ldh.listenerModel.UpLoadImageListenerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class UploadImageListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "otherResource.uploadImage"),
            exchange = @Exchange(name = "ldh.otherResource", type = ExchangeTypes.DIRECT),
            key = {"image"}
    ))
    public void uploadImage(UpLoadImageListenerDTO upLoadImageListenerDTO){
       // UpLoadImageListenerDTO upLoadImageListenerDTO = (UpLoadImageListenerDTO) object ;
        log.debug(upLoadImageListenerDTO.toString());
    }

}

