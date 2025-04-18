package com.xiaou.music.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "u_song")
@Data
public class Song {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    private String url;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
