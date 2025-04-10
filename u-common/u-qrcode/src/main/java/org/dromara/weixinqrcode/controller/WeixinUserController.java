package org.dromara.weixinqrcode.controller;


import cn.hutool.jwt.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.weixinqrcode.model.WeixinQrCode;
import org.dromara.weixinqrcode.util.ApiResultUtil;
import org.dromara.weixinqrcode.util.JwtUtil;
import org.dromara.weixinqrcode.util.WeixinApiUtil;
import org.dromara.weixinqrcode.util.WeixinQrCodeCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/uapi/wxlogin")
@Tag(name = "微信扫码登录")
public class WeixinUserController {

    @Resource
    private WeixinApiUtil weixinApiUtil;

    JwtUtil jwtUtil= new JwtUtil();

    @GetMapping(value = "/user/qrcode")
    @Operation(summary = "获取二维码")
    public String getQrCode() {
        WeixinQrCode qrCode = weixinApiUtil.getQrCode();
        qrCode.setUrl(null);
        qrCode.setExpireSeconds(null);
        return ApiResultUtil.success(qrCode);
    }

    /**
     * 校验是否扫描完成
     * 完成，返回 JWT
     * 未完成，返回 check faild
     *
     * @param ticket
     * @return
     */
    @GetMapping(value = "/user/login/qrcode")
    @Operation(summary = "校验是否扫描完成")
    public String userLogin(String ticket) {
        String openId = WeixinQrCodeCacheUtil.get(ticket);
        if (StringUtils.isNotEmpty(openId)) {
            log.info("login success,open id:{}", openId);
            return ApiResultUtil.success(jwtUtil.createToken(openId));
        }
        log.info("login error,ticket:{}", ticket);
        return ApiResultUtil.error("check faild");
    }

}
