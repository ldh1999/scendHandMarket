package com.ldh.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
public class JwtProperties {
    /**
     * Request Headers ： Authorization
     */
    final private String header = "token";

    /**
     * Base64对该令牌进行编码
     */
    final private String base64Secret = "meng";

    /**
     * 令牌过期时间 此处单位/毫秒
     */
    final private Long tokenValidityInSeconds = 14400000L;

}
