package com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 响应状态枚举
 * @Author: sxgan
 * @Date: 2024/3/1 15:09
 * @Version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum ResponseStatus {


    /* 成功 */
    SUCCESS(200, "success"), /* 失败*/
    FAIL(500, "failed"),

    /*200响应状态*/
    HTTP_STATUS_200(200, "ok"),
    HTTP_STATUS_400(400, "request error"),
    HTTP_STATUS_401(401, "no authentication"),
    HTTP_STATUS_403(403, "no authorities"),
    HTTP_STATUS_500(500, "server error");


    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500)
    );

    /* response code*/
    private final Integer responseCode;

    /* description.*/
    private final String description;

}
