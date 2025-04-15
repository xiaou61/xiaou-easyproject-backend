package com.xiaou.web.domain.vo;

import lombok.Data;

/**
 * 验证码信息
 *
 * @author xiaou
 */
@Data
public class CaptchaVo {

    private String code;

    /**
     * 验证码图片
     */
    private String img;

}
