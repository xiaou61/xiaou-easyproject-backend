package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz;

import lombok.Data;

import java.util.Objects;

@Data
public class RequestFilterChain {

    private RequestFilterHandler requestFilterHandler;

    private RequestFilter requestFilter;

    private RequestFilterChain nextFilterChain;

    public void doFilter(Request request, Response response) {
        if (Objects.nonNull(requestFilter)) {
            if (Objects.isNull(nextFilterChain)) {
                nextFilterChain = new RequestFilterChain();
            }
            requestFilter.doFilter(request, response, nextFilterChain);
        }
    }

}
