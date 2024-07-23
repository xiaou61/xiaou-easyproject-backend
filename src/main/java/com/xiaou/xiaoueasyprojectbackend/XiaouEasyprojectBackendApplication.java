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
    }

}
