package com.xiaou.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiPrefixConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ApiPrefixConfig.class);
    @Value("${xiaou.upload.local-path}")
    private String localPath;

    @Value("${xiaou.upload.access-prefix}")
    private String accessPrefix;

    @Value("${server.servlet.context-path}")
    private String contextPath;  // 自动获取context-path

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8848") // 指定前端地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // 允许携带凭证
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(accessPrefix + "/**")
                .addResourceLocations("file:" + localPath + "/");
    }
}
