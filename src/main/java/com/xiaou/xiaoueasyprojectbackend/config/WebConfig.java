package com.xiaou.xiaoueasyprojectbackend.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("file:D:\\onenodes\\githubprojectstart\\xiaou-easyproject-backend\\src\\main\\java\\com\\xiaou\\xiaoueasyprojectbackend\\common\\upload\\");
    }

}
