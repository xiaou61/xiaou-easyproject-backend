package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz;

import lombok.Data;

import java.util.Map;

@Data
public class Request {

    private Map<String, Object> headers;

    private String requestMethod;

}
