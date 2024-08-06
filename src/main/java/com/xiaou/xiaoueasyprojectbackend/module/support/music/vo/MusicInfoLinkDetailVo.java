package com.xiaou.xiaoueasyprojectbackend.module.support.music.vo;

import lombok.Data;

import java.util.Map;

@Data
public class MusicInfoLinkDetailVo {
    /**
     * 链接来源：0-github，1-alist
     */
    private Integer linkSource;
    /**
     * 链接来源文字描述
     */
    private String linkSourceName;
    /**
     * 链接集合
     */
    Map<String, String> linkMap;

}