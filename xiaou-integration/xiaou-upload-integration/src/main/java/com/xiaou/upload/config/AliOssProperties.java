package com.xiaou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOssProperties {
    //读取配置文件的内容
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

}
