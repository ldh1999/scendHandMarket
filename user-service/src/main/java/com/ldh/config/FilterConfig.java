package com.ldh.config;

import com.ldh.filter.RoleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    //过滤器有点问题暂时关了
   // @Bean
    public FilterRegistrationBean roleFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RoleFilter(redisTemplate));
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
