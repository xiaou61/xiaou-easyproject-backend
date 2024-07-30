package com.xiaou.xiaoueasyprojectbackend.module.support.monitor.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.dto.ResponseDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.RedisCacheInfoDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.ServerInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.service.MonitorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/30 12:28
 * @Description: 缓存监控
 */
@Tag(name = "监控API", description = "监控相关信息")
@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorController{

    @Resource
    private MonitorApplicationService monitorApplicationService;

    @Operation(summary = "Redis信息")
    @GetMapping("/cacheInfo")
    public ResponseDTO<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorApplicationService.getRedisCacheInfo();
        return ResponseDTO.ok(redisCacheInfo);
    }


    @Operation(summary = "服务器信息")
    @GetMapping("/serverInfo")
    public ResponseDTO<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorApplicationService.getServerInfo();
        return ResponseDTO.ok(serverInfo);
    }

}
