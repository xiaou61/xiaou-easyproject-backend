package com.xiaou.xiaoueasyprojectbackend.module.support.music.enums;

public interface BaseRespResult {

    /**
     * 获取响应描述
     *
     * @return 响应描述
     */
    String getMessage();

    /**
     * 获取响应状态码
     *
     * @return 响应状态码
     */
    Integer getCode();

}