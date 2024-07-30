package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.dto;


import java.util.Date;

import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.SysNoticeEntity;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class NoticeDTO {

    public NoticeDTO(SysNoticeEntity entity) {
        if (entity != null) {
            this.noticeId = entity.getNoticeId() + "";
            this.noticeTitle = entity.getNoticeTitle();
            this.noticeType = entity.getNoticeType();
            this.noticeContent = entity.getNoticeContent();
            this.status = entity.getStatus();
            this.createTime = entity.getCreateTime();
            //todo 这里需要根据实际情况来填写
            this.creatorName="xiaou";
        }
    }

    private String noticeId;

    private String noticeTitle;

    private Integer noticeType;

    private String noticeContent;

    private Integer status;

    private Date createTime;

    private String creatorName;

}
