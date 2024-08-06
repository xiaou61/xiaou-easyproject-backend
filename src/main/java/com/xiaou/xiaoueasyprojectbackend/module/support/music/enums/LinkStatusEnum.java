package com.xiaou.xiaoueasyprojectbackend.module.support.music.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum LinkStatusEnum {
    /**
     * 0-未填充链接
     */
    UN_LINKED(0, "未填充链接"),

    /**
     * 1-已填充链接
     */
    LINKED(1, "已填充链接"),
    ;
    private Integer code;
    private String desc;

    public boolean match(Integer code) {
        return Objects.equals(code, this.code);
    }

}