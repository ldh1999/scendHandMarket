package com.ldh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/*@Configuration*/
/*@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)*/
public class SessionConfig {

    // 冒号后的值为没有配置文件时，制动装载的默认值
    @Value("${redis.hostname:localhost}")
    String HostName;
    @Value("${redis.port:6379}")
    int Port;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setPort(Port);
        connection.setHostName(HostName);
        return connection;
    }
}
