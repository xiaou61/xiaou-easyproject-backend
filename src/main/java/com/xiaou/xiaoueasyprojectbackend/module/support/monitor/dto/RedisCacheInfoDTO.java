package com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto;

import lombok.Data;
import java.util.Properties;
import java.util.List;

@Data
public class RedisCacheInfoDTO {

    private Properties info;
    private Long dbSize;
    private List<CommandStatusDTO> commandStats;

    @Data
    public static class CommandStatusDTO {
        private String name;
        private String value;
    }

}