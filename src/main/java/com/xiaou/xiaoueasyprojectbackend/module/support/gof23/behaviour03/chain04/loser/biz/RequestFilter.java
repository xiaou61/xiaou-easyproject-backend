package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz;

public interface RequestFilter {

    void doFilter(Request request, Response response, RequestFilterChain filterChain);

    default int sort() {
        return 1;
    }

}
