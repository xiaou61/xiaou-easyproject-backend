package com.xiaou.xiaoueasyprojectbackend;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//启动文件上传服务
@EnableFileStorage
@MapperScan("com.xiaou.xiaoueasyprojectbackend.mapper")
public class XiaouEasyprojectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaouEasyprojectBackendApplication.class, args);
        String successMsg = "  ____   _                _                                                           __         _  _ \n"
                + " / ___| | |_  __ _  _ __ | |_   _   _  _ __    ___  _   _   ___  ___  ___  ___  ___  / _| _   _ | || |\n"
                + " \\___ \\ | __|/ _` || '__|| __| | | | || '_ \\  / __|| | | | / __|/ __|/ _ \\/ __|/ __|| |_ | | | || || |\n"
                + "  ___) || |_| (_| || |   | |_  | |_| || |_) | \\__ \\| |_| || (__| (__|  __/\\__ \\\\__ \\|  _|| |_| || ||_|\n"
                + " |____/  \\__|\\__,_||_|    \\__|  \\__,_|| .__/  |___/ \\__,_| \\___|\\___|\\___||___/|___/|_|   \\__,_||_|(_)\n"
                + "                                      |_|                                                             ";

        System.out.println(successMsg);
    }

}
