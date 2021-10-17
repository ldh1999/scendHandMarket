package com.ldh.config;

import com.ldh.listen.UserListen;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenConfig {
    @Bean
    public ServletListenerRegistrationBean userListener(){
        ServletListenerRegistrationBean<UserListen> listenerRegistrationBean = new ServletListenerRegistrationBean<>(new UserListen());
        return listenerRegistrationBean;
    }
}
