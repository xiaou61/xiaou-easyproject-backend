package com.xiaou.xiaoueasyprojectbackend.common.domain.model.dto.user;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class CaptchaDTO {

    private Boolean isCaptchaOn;
    private String captchaCodeKey;
    private String captchaCodeImg;

}
