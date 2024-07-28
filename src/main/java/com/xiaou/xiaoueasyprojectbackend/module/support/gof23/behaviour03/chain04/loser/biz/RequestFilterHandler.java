package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.Component;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.ContextUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RequestFilterHandler {

    private List<RequestFilterChain> chains;

    private RequestFilterChain getLastChain() {

        if (Objects.nonNull(chains)) {
            if (chains.size() > 0) {
                return chains.get(chains.size() - 1);
            }
        }
        init();
        if (Objects.nonNull(chains)) {
            if (chains.size() > 0) {
                return chains.get(chains.size() - 1);
            }
        }
        return null;

    }

    private RequestFilterChain getFirstChain() {

        if (Objects.nonNull(chains)) {
            if (chains.size() > 0) {
                return chains.get(0);
            }
        }
        init();
        if (Objects.nonNull(chains)) {
            if (chains.size() > 0) {
                return chains.get(0);
            }
        }
        return null;

    }

    private void init() {
        synchronized (RequestFilterHandler.class) {
            if (Objects.nonNull(chains)) {
                return;
            }
            List<RequestFilter> beans = ContextUtils.getBeans(RequestFilter.class);
            if (Objects.nonNull(beans) && beans.size() > 0) {
                List<RequestFilter> filters = beans.stream().sorted(Comparator.comparing(RequestFilter::sort)).collect(Collectors.toList());
                int index = filters.size() - 1;
                List<RequestFilterChain> list = new ArrayList<>();
                for (int i = index; i >= 0; i--) {
                    RequestFilter requestFilter = filters.get(i);
                    RequestFilterChain chain = new RequestFilterChain();
                    chain.setRequestFilter(requestFilter);
                    if (list.size() > 0) {
                        chain.setNextFilterChain(list.get(0));
                    }
                    list.add(0, chain);
                }
                chains = list;
            } else {
                chains = Collections.emptyList();
            }
        }
    }


    public void doFilter(Request request, Response response) {

        RequestFilterChain firstChain = getFirstChain();
        if (Objects.nonNull(firstChain) && Objects.nonNull(firstChain.getRequestFilter())) {
            firstChain.doFilter(request, response);
        }

        doBiz(request, response);

    }

    public void doBiz(Request request, Response response) {

        System.out.println();
        System.out.println("执行业务");
        System.out.println();

    }
}
