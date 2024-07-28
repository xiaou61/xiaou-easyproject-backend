package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.impl;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Request;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.RequestFilter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.RequestFilterChain;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Response;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.Component;

import java.util.Objects;

@Component
public class IpRequestFilter implements RequestFilter {

    @Override
    public void doFilter(Request request, Response response, RequestFilterChain filterChain) {
        System.out.println("IpRequestFilter index " + sort() + " " + request);
        if (Objects.nonNull(request.getHeaders().get("ip"))) {
            filterChain.doFilter(request, response);
        } else {
            throw new RuntimeException("ip 为空");
        }
    }

    @Override
    public int sort() {
        return 2;
    }

}
