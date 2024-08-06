package com.xiaou.xiaoueasyprojectbackend.module.support.music.config;

import com.xiaou.xiaoueasyprojectbackend.module.support.music.enums.MusicLinkSourceEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



@Data
@Configuration
@Component
public class AppConfig {


    /**
     * 作者名称
     */
    @Value("${author.name:woodwhales}")
    public String authorName;

    /**
     * 作者网站
     */
    @Value("${author.website:https://www.woodwhales.cn}")
    public String authorWebsite;

    @Value("${music.link.source}")
    public Integer musicLinkSource;

    public MusicLinkSourceEnum getMusicLinkSourceEnum() {
        return MusicLinkSourceEnum.ofCode(this.getMusicLinkSource());
    }

}
