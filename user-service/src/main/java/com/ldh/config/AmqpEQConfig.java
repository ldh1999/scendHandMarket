package com.ldh.config;

import constant.AmqpConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpEQConfig {

    @Bean
    public Queue userInsert(){
        return new Queue(AmqpConstant.USER_INSERT_QUEUE);
    }

    @Bean
    public Queue userDelete(){
        return new Queue(AmqpConstant.USER_DELETE_QUEUE);
    }

    @Bean
    public TopicExchange userExchange(){
        return new TopicExchange(AmqpConstant.USER_EXCHANGE);
    }

    // 绑定用户添加到交换机
    @Bean
    public Binding userExchangeBindingInsert(Queue userInsert, TopicExchange userExchange){
        return BindingBuilder
                .bind(userInsert)
                .to(userExchange)
                .with("user.insert");
    }

    // 绑定用户删除到交换机
    @Bean
    public Binding userExchangeBindingDelete(Queue userDelete, TopicExchange userExchange){
        return BindingBuilder
                .bind(userDelete)
                .to(userExchange)
                .with("user.delete");
    }

}
