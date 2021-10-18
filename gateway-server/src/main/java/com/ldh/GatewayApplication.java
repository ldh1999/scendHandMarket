package com.ldh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication()
public class GatewayApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext application = SpringApplication.run(GatewayApplication.class,args);
    }
}
