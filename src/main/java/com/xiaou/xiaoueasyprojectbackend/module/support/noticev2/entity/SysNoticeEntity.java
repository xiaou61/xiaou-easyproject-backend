package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_notice")
public class SysNoticeEntity extends BaseEntity<SysNoticeEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    @TableField("notice_title")
    private String noticeTitle;

    @TableField("notice_type")
    private Integer noticeType;

    @TableField("notice_content")
    private String noticeContent;

    @TableField("`status`")
    private Integer status;

    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.noticeId;
    }

}
