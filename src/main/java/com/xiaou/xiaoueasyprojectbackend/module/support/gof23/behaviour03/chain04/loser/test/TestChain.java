package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Request;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.RequestFilterHandler;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.biz.Response;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.chain04.loser.core.ContextUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 推荐指数：★★★★★
 * <p>
 * 责任链是一种行为设计模式，允许你将请求沿着处理者链进行发送。收到请求后，每个处理者均可对请求进行处理，或将其传递给链上的下个处理者。
 * <p>
 * 模拟请求规律器
 */
public class TestChain {

    @Test
    public void test() {

        // 模拟请求过滤器
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);

        RequestFilterHandler requestFilterHandler = ContextUtils.getBean(RequestFilterHandler.class);

        Request request = new Request();
        Map<String, Object> heads = new HashMap<>();
        heads.put("ip", "127.0.0.1");
        heads.put("token", "loginToken");
        request.setHeaders(heads);
        request.setRequestMethod("POST");

        requestFilterHandler.doFilter(request, new Response());

    }

}
