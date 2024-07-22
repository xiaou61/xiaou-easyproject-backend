package com.xiaou.xiaoueasyprojectbackend.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 项目启动成功日志打印监听器
 */
@Component
@Slf4j
public class StartedListener implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 项目启动成功将会在日志中输出对应的启动信息
     *
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        String serverPort = applicationReadyEvent.getApplicationContext().getEnvironment().getProperty("server.port");
        String serverUrl = String.format("http://%s:%s", "127.0.0.1", serverPort);
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "u-easyproject started at: ", serverUrl));
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "u-easyproject server's doc started at:", serverUrl + "/doc.html"));
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "u-easyproject server has started successfully!"));
    }
}