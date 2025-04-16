package com.xiaou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class ApiPrefixConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPatternParser(new PathPatternParser());

        // 只给加了 @RestController 注解的类添加前缀
        configurer.addPathPrefix("/uapi", c -> c.isAnnotationPresent(org.springframework.web.bind.annotation.RestController.class));
    }
}
