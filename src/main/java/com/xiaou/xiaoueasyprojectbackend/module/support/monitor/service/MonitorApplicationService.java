package com.xiaou.xiaoueasyprojectbackend.module.support.monitor.service;

import cn.hutool.core.util.StrUtil;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.RedisCacheInfoDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.RedisCacheInfoDTO.CommandStatusDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.ServerInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MonitorApplicationService {


    private final RedisTemplate<String, ?> redisTemplate;

    public RedisCacheInfoDTO getRedisCacheInfo() {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
        Properties commandStats = (Properties) redisTemplate.execute(
                (RedisCallback<Object>) connection -> connection.info("commandstats"));
        Long dbSize = redisTemplate.execute(RedisServerCommands::dbSize);

        if (commandStats == null || info == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "获取Redis监控信息失败。");
        }

        RedisCacheInfoDTO cacheInfo = new RedisCacheInfoDTO();

        cacheInfo.setInfo(info);
        cacheInfo.setDbSize(dbSize);
        cacheInfo.setCommandStats(new ArrayList<>());

        commandStats.stringPropertyNames().forEach(key -> {
            String property = commandStats.getProperty(key);

            CommandStatusDTO commonStatus = new CommandStatusDTO();
            commonStatus.setName(StrUtil.removePrefix(key, "cmdstat_"));
            commonStatus.setValue(StrUtil.subBetween(property, "calls=", ",usec"));

            cacheInfo.getCommandStats().add(commonStatus);
        });

        return cacheInfo;
    }

    public ServerInfo getServerInfo() {
        return ServerInfo.fillInfo();
    }
}
