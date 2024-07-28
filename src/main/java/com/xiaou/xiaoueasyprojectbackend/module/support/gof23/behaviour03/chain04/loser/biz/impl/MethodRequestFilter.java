package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Request;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.RequestFilter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.RequestFilterChain;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Response;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.Component;

@Component
public class MethodRequestFilter implements RequestFilter {

    @Override
    public void doFilter(Request request, Response response, RequestFilterChain filterChain) {

        System.out.println("MethodRequestFilter index " + sort() + " " + request);
        if ("POST".equals(request.getRequestMethod())) {
            filterChain.doFilter(request, response);
        } else {
            throw new RuntimeException("不支持方法类型");
        }

    }

}
