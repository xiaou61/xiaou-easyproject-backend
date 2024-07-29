package com.xiaou.xiaoueasyprojectbackend.module.support.sms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/29 13:40
 * @Description 发送短信 用到了开源项目：https://github.com/dromara/SMS4J
 * 可以参考这个：https://sms4j.com/doc3/supplierConfig.html来配置短信
 */
@RestController
@RequestMapping("/v1/sms")
@Tag(name = "短信发送模块V1", description = "短信发送模块")
public class SmsControllerV1 {
    @GetMapping
    public void send() {
        //阿里云向此手机号发送短信
        SmsFactory.getSmsBlend("自定义标识1").sendMessage("18888888888","123456");
        //华为短信向此手机号发送短信
        SmsFactory.getSmsBlend("自定义标识2").sendMessage("16666666666","000000");
    }

}
