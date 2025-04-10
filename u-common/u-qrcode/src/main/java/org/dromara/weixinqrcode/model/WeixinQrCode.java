package org.dromara.weixinqrcode.model;

import lombok.Data;


@Data
public class WeixinQrCode {

    private String ticket;
    private Long expireSeconds;
    private String url;
    private String qrCodeUrl;
}
