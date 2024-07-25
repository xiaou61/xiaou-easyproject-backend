package com.xiaou.xiaoueasyprojectbackend.module.support.captcha.service;

import com.xiaou.xiaoueasyprojectbackend.common.utils.StringUtils;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class MailService {
    /**
     * 注入邮件工具类
     */
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 检测邮件信息类
     * @param receiveEmail 接收者
     * @param subject  主题
     * @param emailMsg 内容
     */
    private void checkMail(String receiveEmail, String subject, String emailMsg){
        //  StringUtils 需要引入  commons-lang3 依赖
        //  可以用 receiveEmail == null || receiveEmail.isEmpty() 来代替
        if(StringUtils.isEmpty(receiveEmail)) {
            throw new RuntimeException("邮件收件人不能为空");
        }
        if(StringUtils.isEmpty(subject)) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if(StringUtils.isEmpty(emailMsg)) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }
    
    /**
     * 发送纯文本邮件
     * @param receiveEmail 接收者
     * @param subject  主题
     * @param emailMsg 内容
     */
    public Boolean sendTextMail(String receiveEmail, String subject, String emailMsg) {
        // 参数检查
        checkMail(receiveEmail, subject, emailMsg);
        try {
            // true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            // 邮件发件人
            mimeMessageHelper.setFrom(sendMailer);
            // 邮件收件人
            mimeMessageHelper.setTo(receiveEmail.split(","));
            // 邮件主题
            mimeMessageHelper.setSubject(subject);
            // 邮件内容
            mimeMessageHelper.setText(emailMsg);
            // 邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
    
            // 发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功: " + sendMailer + "-->" + receiveEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败: " + e.getMessage());
            return false;
        }
    }
}
