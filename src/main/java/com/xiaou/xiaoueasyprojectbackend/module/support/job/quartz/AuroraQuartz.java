package com.xiaou.xiaoueasyprojectbackend.module.support.job.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("auroraQuartz")
public class AuroraQuartz {
    public void test() {
        log.info("auroraQuartz test");
    }
}
