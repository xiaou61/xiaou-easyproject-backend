package org.dromara.qrcodelogin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {

    /**
     * 唯一标识
     */
    private String uuid;
    /**
     * 登录二维码
     */
    private String qrcode;

    /**
     * jwt令牌
     */
    private String token;

    /**
     * 扫码状态
     */
    private String status;
}
