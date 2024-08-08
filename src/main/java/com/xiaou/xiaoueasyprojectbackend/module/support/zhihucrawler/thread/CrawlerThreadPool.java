package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.thread;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：爬取自定义线程池，限制并发数
 *
 * @author zwl
 * @since 2022/6/1 16:58
 **/
@Slf4j
public class CrawlerThreadPool extends ThreadPoolExecutor {

    private final RateLimiter rateLimiter;

    public CrawlerThreadPool(int threadNum) {
        super(threadNum, threadNum << 3, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("crawler-%d").build(), (r, executor) -> executor.execute(r));
        this.rateLimiter = RateLimiter.create(threadNum);
    }

    /**
     * 描述：限制并发数
     *
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        rateLimiter.acquire();
    }

}
