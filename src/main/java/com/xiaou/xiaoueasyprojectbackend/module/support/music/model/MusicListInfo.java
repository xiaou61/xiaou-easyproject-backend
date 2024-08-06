package com.xiaou.xiaoueasyprojectbackend.module.support.music.model;

import lombok.Data;

@Data
public class MusicListInfo {

    /**
     * 收录总量
     */
    private Integer totalCount;

    private String musicList;

    private int musicListRows;

    public MusicListInfo(Integer totalCount, String musicList, int musicListRows) {
        this.totalCount = totalCount;
        this.musicList = musicList;
        this.musicListRows = musicListRows;
    }
}
