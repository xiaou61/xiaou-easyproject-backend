package com.xiaou.xiaoueasyprojectbackend.module.support.captcha.controller;

import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.Producer;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.config.CaptchaConfig;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.config.KaptchaTextCreator;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.constant.CacheConstants;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.text.Constants;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.utils.IdUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.utils.RedisCache;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaou61
 * @Date 2024/7/22 下午2:53
 * @Description 验证码生成 若依版本 这里除了import的之外 还要导入这个{@link KaptchaTextCreator}
 * 并且需要修改{@link CaptchaConfig#getKaptchaBeanMath()}  里面验证码文本生成器的包名
 */
@RestController
@RequestMapping("/v2/captcha")
public class CaptchaControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(CaptchaControllerV2.class);
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        //验证码类别 我这里做了修改 采用随机的
        String captchaType = new Random().nextBoolean() ? "math" : "char";

        log.info("验证码类别:{}", captchaType);
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
            log.info("验证码:" + capStr + "@验证码运算结果:" + code);

        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
            log.info("验证码:" + capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}
