package com.xiaou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "xiaou.upload")
public class OssProperties {

    /**
     * 本地上传路径（结尾带 /）
     */
    private String localPath;

    /**
     * 访问 URL 前缀（如 /upload-files）
     */
    private String accessPrefix;

    /**
     * 服务域名或地址
     */
    private String serviceHost;
}
