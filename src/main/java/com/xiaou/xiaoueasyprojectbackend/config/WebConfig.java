package com.xiaou.xiaoueasyprojectbackend.config;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.interceptor.AuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("file:D:\\onenodes\\githubprojectstart\\xiaou-easyproject-backend\\src\\main\\java\\com\\xiaou\\xiaoueasyprojectbackend\\common\\upload\\");
    }
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**"); // 配置拦截的路径
    }
}
