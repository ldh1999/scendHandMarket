package com.ldh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication()
public class UserApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext application = SpringApplication.run(UserApplication.class,args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        System.out.println("Swagger:http://localhost:"+port+"/swagger-ui.html");
    }
}
