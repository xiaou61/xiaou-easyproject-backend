package com.xiaou.xiaoueasyprojectbackend.controller.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.xiaou.xiaoueasyprojectbackend.common.core.domain.BaseResponse;
import com.xiaou.xiaoueasyprojectbackend.common.core.domain.R;
import com.xiaou.xiaoueasyprojectbackend.common.core.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.common.domain.model.entity.ToEmail;
import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.service.MailService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/25 18:42
 * 这个是用qq邮箱发送验证码的功能
 */
@RestController
@RequestMapping("/v3/captcha")
public class CaptchaControllerV3 {
    @Resource
    private MailService mailService;

    @RequestMapping("/getCode")
    public BaseResponse sendEmail(@RequestParam String to, HttpServletRequest request) {

        ToEmail toEmail = new ToEmail();
        toEmail.setTos(to);
        toEmail.setSubject("你本次的验证码是");
        // 使用hutools工具获取验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        String verCode = captcha.getCode();
        String content = "尊敬的xxx,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）";
        toEmail.setContent(content);

        Boolean check = mailService.sendTextMail(toEmail.getTos(), toEmail.getSubject(), toEmail.getContent());
        if (check) {
            return ResultUtils.success("发送成功");
        } else {
            return ResultUtils.error("发送失败");
        }

    }

}
