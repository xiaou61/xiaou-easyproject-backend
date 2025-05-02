package com.xiaou.hot.job.cycle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaou.hot.manager.DataSourceRegistry;
import com.xiaou.hot.model.enums.HotDataKeyEnum;
import com.xiaou.hot.model.po.HotPost;
import com.xiaou.hot.service.HotPostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 自动同步热榜数据
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class IncSyncHostPostToMySQL {

    private final DataSourceRegistry dataSourceRegistry;
    private final HotPostService hotPostService;


    /**
     * 应用启动时执行一次
     */
    @PostConstruct
    public void initRun() {
        log.info("应用启动，立即执行一次更新热榜任务...");
        run();
    }


    /**
     * 每半小时执行一次
     */
    @Scheduled(fixedRate = 1_800_000)
    public void run() {
        log.info("开始更新热榜数据...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HotDataKeyEnum.getValues().forEach(key -> {
            boolean success = false;
            int maxAttempts = 3;

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                try {
                    updateHotPost(key);
                    success = true;
                    break; // 成功则跳出重试
                } catch (Exception e) {
                    log.warn("更新热榜失败");
                    try {
                        Thread.sleep(2000); // 可选：每次失败后延迟 2 秒再试
                    } catch (InterruptedException ignored) {
                    }
                }
            }

            if (!success) {
                log.error("更新热榜数据失败，已达到最大重试次数，放弃更新【{}】", key);
            }
        });

        stopWatch.stop();
        log.info("更新热榜数据完成，耗时：{}ms", stopWatch.getTotalTimeMillis());
    }


    private void updateHotPost(String key) {
        LambdaQueryWrapper<HotPost> hotPostLambdaQueryWrapper = new LambdaQueryWrapper<>();
        hotPostLambdaQueryWrapper.eq(HotPost::getType, key);
        HotPost oldHotPost = hotPostService.getOne(hotPostLambdaQueryWrapper);
        if (oldHotPost != null) {
            //如果更新时间间隔未到直接跳过
            //小时制
            BigDecimal updateInterval = oldHotPost.getUpdateInterval();
            //转成时间戳
            long updateIntervalMillis = updateInterval.multiply(new BigDecimal(60 * 60 * 1000)).longValue();
            if (oldHotPost.getUpdateTime().getTime() + updateIntervalMillis > System.currentTimeMillis()) {
                log.info("加载===========>【{}】热榜数据跳过", HotDataKeyEnum.getEnumByValue(key).getText());
                return;
            }
        }
        HotPost hotPost = dataSourceRegistry.getDataSourceByType(key).getHotPost();
        hotPost.setType(key);
        if (oldHotPost != null) {
            hotPost.setId(oldHotPost.getId());
        }
        hotPost.setUpdateTime(new Date());
        hotPostService.saveOrUpdate(hotPost);
        log.info("加载===========>【{}】热榜数据完成", hotPost.getTypeName());
    }
}
