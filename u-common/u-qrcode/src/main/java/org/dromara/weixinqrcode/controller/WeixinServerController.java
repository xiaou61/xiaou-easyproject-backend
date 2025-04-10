package org.dromara.weixinqrcode.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.weixinqrcode.service.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/uapi/wxlogin")
@Tag(name = "微信登录")
public class WeixinServerController {

    @Resource
    private WeixinUserService weixinUserService;

    @GetMapping(value = "/weixin/check")
    @Operation(summary = "微信服务器验证")
    public String weixinCheck(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
            return "";
        }
        weixinUserService.checkSignature(signature, timestamp, nonce);
        return echostr;
    }

    @PostMapping(value = "/weixin/check")
    @Operation(summary = "微信服务器验证")
    public String weixinMsg(@RequestBody String requestBody, @RequestParam("signature") String signature,
        @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {

        log.debug("requestBody:{}", requestBody);
        log.debug("signature:{}", signature);
        log.debug("timestamp:{}", timestamp);
        log.debug("nonce:{}", nonce);

        weixinUserService.checkSignature(signature, timestamp, nonce);
        return weixinUserService.handleWeixinMsg(requestBody);
    }

}
