package com.xiaou.xiaoueasyprojectbackend.common.config;

import com.alibaba.dashscope.aigc.generation.Generation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public Generation generation(){
        return new Generation();
    }
}
