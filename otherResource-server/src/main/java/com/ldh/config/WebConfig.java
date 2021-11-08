package com.ldh.config;

import com.ldh.modules.upload.constant.FilePath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置虚拟路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+FilePath.IMAGE_SAVE_PATH);
    }
}
