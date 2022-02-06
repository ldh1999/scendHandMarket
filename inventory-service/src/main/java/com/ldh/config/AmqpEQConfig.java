package com.ldh.config;

import constant.AmqpConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpEQConfig {

    @Bean
    public Queue preferencesInsert(){
        return new Queue(AmqpConstant.PREFERENCES_OPERATION_QUEUE);
    }

    @Bean
    public TopicExchange preferencesExchange(){
        return new TopicExchange(AmqpConstant.PREFERENCES_EXCHANGE);
    }

    // 绑定用户添加到交换机
    @Bean
    public Binding preferencesExchangeBindingInsert(Queue preferencesInsert, TopicExchange preferencesExchange){
        return BindingBuilder
                .bind(preferencesInsert)
                .to(preferencesExchange)
                .with("preferences.insert");
    }
}
