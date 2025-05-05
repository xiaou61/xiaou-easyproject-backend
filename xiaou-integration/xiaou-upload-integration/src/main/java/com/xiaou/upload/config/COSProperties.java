package com.xiaou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tencent.cos")
public class COSProperties {

    /** 密钥ID */
    private String secretId;

    /** 密钥KEY */
    private String secretKey;

    /** 区域名 */
    private String region;

    /** 桶名 */
    private String bucketName;

    /** COS访问前缀 */
    private String baseUrl;
}
