package com.ldh;

import com.ldh.config.DefaultFeginConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertyResolver;

@SpringBootApplication()
@EnableFeignClients(basePackages = "com.ldh", defaultConfiguration = DefaultFeginConfiguration.class)
public class InventoryApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext application = SpringApplication.run(InventoryApplication.class,args);
        PropertyResolver env = application.getEnvironment();
        String port = env.getProperty("server.port");
        System.out.println("Swagger:http://localhost:"+port+"/swagger-ui.html");
    }
}
