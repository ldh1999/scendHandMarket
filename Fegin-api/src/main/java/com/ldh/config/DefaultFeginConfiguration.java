package com.ldh.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeginConfiguration {

    @Bean
    public Logger.Level log(){
        return Logger.Level.BASIC;
    }
}
