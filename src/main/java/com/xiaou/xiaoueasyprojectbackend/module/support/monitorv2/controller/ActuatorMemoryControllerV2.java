package com.xiaou.xiaoueasyprojectbackend.module.support.monitorv2.controller;

import cn.hutool.core.util.NumberUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitorv2.entity.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaou61
 * @Date 2024/7/31 10:49
 * @Description: 内存监控
 */
@Slf4j
@RestController
@RequestMapping("/v2/actuator/memory")
@Tag(name = "内存监控V2")
public class ActuatorMemoryControllerV2 {


    /**
     * 内存详情
     * @return
     */
    @GetMapping("/info")
    public Result<?> getRedisInfo() {
		Runtime runtime = Runtime.getRuntime();
		Map<String,Number> result = new HashMap<>();
		result.put("memory.runtime.total", runtime.totalMemory());
		result.put("memory.runtime.used", runtime.freeMemory());
		result.put("memory.runtime.max", runtime.totalMemory() - runtime.freeMemory());
		result.put("memory.runtime.free", runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory());
		result.put("memory.runtime.usage", NumberUtil.div(runtime.totalMemory() - runtime.freeMemory(), runtime.totalMemory()));
		//update-begin---author:chenrui ---date:20240705  for：[TV360X-1695]内存信息-立即更新 功能报错 #6635------------
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		if (operatingSystemMXBean instanceof com.sun.management.OperatingSystemMXBean) {
			com.sun.management.OperatingSystemMXBean opBean = (com.sun.management.OperatingSystemMXBean) operatingSystemMXBean;
//			JSONObject operatingSystemJson = JSONObject.parseObject(JSONObject.toJSONString(operatingSystemMXBean));
			long totalPhysicalMemory = opBean.getTotalPhysicalMemorySize();
			long freePhysicalMemory = opBean.getFreePhysicalMemorySize();
			long usedPhysicalMemory = totalPhysicalMemory - freePhysicalMemory;
			result.put("memory.physical.total", totalPhysicalMemory);
			result.put("memory.physical.used", freePhysicalMemory);
			result.put("memory.physical.free", usedPhysicalMemory);
			result.put("memory.physical.usage", NumberUtil.div(usedPhysicalMemory, totalPhysicalMemory));
		}
		//update-end---author:chenrui ---date:20240705  for：[TV360X-1695]内存信息-立即更新 功能报错 #6635------------
		return Result.ok(result);
    }

}
