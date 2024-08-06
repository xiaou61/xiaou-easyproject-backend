package com.xiaou.xiaoueasyprojectbackend.module.support.music.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName(value= "music_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MusicInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 音乐表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 音乐名称标题（音乐名称）
     */
    @TableField(value = "title")
    private String title;

    /**
     * 作者
     */
    @TableField(value = "artist")
    private String artist;

    /**
     * 专辑
     */
    @TableField(value = "album")
    private String album;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 是否删除，0-已启用，1-已停用，2-已删除
     */
    @TableLogic(delval = "2")
    @TableField(value = "status")
    private Byte status;

    /**
     * 链接填充状态：0-未填充链接，1-已填充链接
     */
    @TableField(value = "link_status")
    private Integer linkStatus;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private java.util.Date gmtCreated;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    private java.util.Date gmtModified;

}