package com.xiaou.xiaoueasyprojectbackend.module.support.music.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName(value= "music_info_link")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicInfoLink implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是否删除，0-已启用，1-已停用，2-已删除
     */
    @TableLogic(delval = "2")
    @TableField(value = "status")
    private Byte status;

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

    /**
     * 
     */
    @TableField(value = "link_url")
    private String linkUrl;

    /**
     * 链接类型：0-音频，1-封面
     */
    @TableField(value = "link_type")
    private Integer linkType;

    /**
     * 链接来源：0-github，1-alist
     */
    @TableField(value = "link_source")
    private Integer linkSource;

    /**
     * music表id
     */
    @TableField(value = "music_id")
    private Long musicId;

}