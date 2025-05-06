package com.xiaou.upload.config;

import com.obs.services.ObsClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("OBSProperties")
@ConfigurationProperties(prefix = "huawei.obs")
public class ObsProperties {
 
    private String endpoint;
    private String accessKey;
    private String secretAccessKey;
    private String bucketName;
    private Long expiration;


}