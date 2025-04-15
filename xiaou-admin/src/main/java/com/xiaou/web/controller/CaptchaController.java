package com.xiaou.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.xiaou.utils.R;
import com.xiaou.web.domain.vo.CaptchaVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//todo satoken
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@Tag(name = "验证码")
public class CaptchaController {

    /**
     * 生成验证码 hutool
     */
    @GetMapping("/auth/code")
    public R<CaptchaVo> getCode() {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        String code = lineCaptcha.getCode();
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setCode(code);
        captchaVo.setImg(lineCaptcha.getImageBase64());
        return R.ok(captchaVo);
    }
}
