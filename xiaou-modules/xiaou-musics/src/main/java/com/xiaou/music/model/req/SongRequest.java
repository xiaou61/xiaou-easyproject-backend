package com.xiaou.music.model.req;

import lombok.Data;

import java.util.Date;

@Data
public class SongRequest {

    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    private Date createTime;

    private Date updateTime;

    private String pic;

    private String lyric;

    private String url;
}
