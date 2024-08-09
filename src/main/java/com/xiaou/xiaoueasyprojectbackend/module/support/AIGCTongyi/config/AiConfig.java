package com.xiaou.xiaoueasyprojectbackend.module.support.AIGCTongyi.config;

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
