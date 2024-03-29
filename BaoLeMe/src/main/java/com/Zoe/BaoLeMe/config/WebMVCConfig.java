package com.Zoe.BaoLeMe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
@Slf4j
public class WebMVCConfig extends WebMvcConfigurationSupport{
//    设置静态资源映射
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        log.info("开始静态映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}
