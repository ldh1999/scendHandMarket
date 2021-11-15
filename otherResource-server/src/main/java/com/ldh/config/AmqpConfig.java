package com.ldh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 第一次启动初始化 */
/*@Configuration*/
public class AmqpConfig {

    /** 配置序列化 */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public FanoutExchange otherResourceFanoutExchange(){
        return new FanoutExchange("ldh.otherResource");
    }

    @Bean
    public Queue uploadImageQueue(){
        return new Queue("otherResource.uploadImage");
    }

    /** 外部资源上传 */
    @Bean
    public Binding otherResourceUploadImage(Queue uploadImageQueue, FanoutExchange otherResourceFanoutExchange){
        return BindingBuilder
                .bind(uploadImageQueue)
                .to(otherResourceFanoutExchange);
    }


}
