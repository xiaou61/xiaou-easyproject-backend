package org.dromara.qrcodelogin.enums;

import lombok.Getter;

@Getter
public enum LoginStatusEnum {
    UNSCANNED("未扫描"),
    SCANNED("已扫描"),
    CONFIRMED("已确认");

    private String desc;


    LoginStatusEnum(String desc) {
        this.desc = desc;
    }
}
