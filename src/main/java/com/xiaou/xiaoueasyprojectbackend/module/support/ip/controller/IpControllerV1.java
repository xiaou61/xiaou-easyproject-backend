package com.xiaou.xiaoueasyprojectbackend.module.support.ip.controller;

import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.ip.entity.IpEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.ip.utils.IpUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/26 22:00
 * @Description: 获取ip功能 整合Ip2region
 */
@RestController
@RequestMapping("/v1/ip")
@Tag(name = "获取ip功能V1", description = "获取ip功能")
public class IpControllerV1 {

    @GetMapping("/getIp")

    public BaseResponse<IpEntity> getIp(HttpServletRequest request){
        IpEntity ipEntity = new IpEntity();

        String ip = IpUtils.getIp(request);
        String cityInfo = IpUtils.getCityInfo(ip);
        ipEntity.setIp(ip);
        ipEntity.setIpInfo(cityInfo);
        return ResultUtils.success(ipEntity);
    }
}
