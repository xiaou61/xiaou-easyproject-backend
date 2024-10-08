

package com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告管理

 * @date 2019-04-18 21:21:40
 */
@Data
@TableName("tz_notice")
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    @TableId
    private Long id;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 状态(1:公布 0:撤回)
     */
    private Integer status;

    /**
     * 是否置顶（1:是 0：否）
     */
    private Integer isTop;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
