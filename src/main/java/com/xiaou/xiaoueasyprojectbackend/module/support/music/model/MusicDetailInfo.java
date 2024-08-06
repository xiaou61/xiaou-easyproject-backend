package com.xiaou.xiaoueasyprojectbackend.module.support.music.model;

import com.xiaou.xiaoueasyprojectbackend.module.support.music.vo.MusicInfoLinkDetailVo;
import lombok.Data;

import java.util.List;

@Data
public class MusicDetailInfo {

    /**
     * 音乐表主键
     */
    private Long id;

    /**
     * 音乐名称标题（音乐名称）
     */
    private String title;

    /**
     * 音乐链接集合
     */
    private List<MusicInfoLinkDetailVo> linkList;

    /**
     * 作者
     */
    private String artist;

    /**
     * 专辑
     */
    private String album;

    /**
     * 排序
     */
    private Integer sort;

}
