package com.xiaou.xiaoueasyprojectbackend.module.support.captcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author xiaou61
 * @Date 2024/7/17 下午2:02
 * @Description 验证码生成
 */
@Tag(name = "验证码生成")
@RestController
@RequestMapping("/v1/captcha")
public class CaptchaControllerV1 {


    @Resource
    private DefaultKaptcha captchaProducer;


    private static final Logger log = LoggerFactory.getLogger(CaptchaControllerV1.class);

    /**
     * @Author xiaou61
     * @Date 2024/7/17 下午2:03
     * @Description 使用hutool完成验证码生成
     * @Since version 1.0
     */
    @Operation(description = "使用hutool完成验证码生成")
    @GetMapping("/hutool")
    public void HutoolVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(response.getOutputStream());
        //获取验证码中的文字内容
        String verifyCode = captcha.getCode();
        log.info("验证码为:{}", verifyCode);
        request.getSession().setAttribute("verifyCode", verifyCode);
    }


    /**
     * @Author xiaou61
     * @Date 2024/7/17 下午2:11
     * @Description 使用Kaptcha完成验证码生成
     * @Since version 1.0
     */
    @Operation(description = "使用Kaptcha完成验证码生成")
    @GetMapping("/kaptcha")
    public void KaptchaVerify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            log.info("验证码为:{}", verifyCode);
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


}
