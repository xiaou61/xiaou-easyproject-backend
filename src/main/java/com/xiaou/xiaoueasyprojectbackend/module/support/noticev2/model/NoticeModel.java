package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.model;

import cn.hutool.core.bean.BeanUtil;

import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeAddCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeUpdateCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.SysNoticeEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums.NoticeTypeEnum;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums.StatusEnum;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.util.BasicEnumUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NoticeModel extends SysNoticeEntity {

    public NoticeModel(SysNoticeEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadAddCommand(NoticeAddCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "noticeId");
        }
    }

    public void loadUpdateCommand(NoticeUpdateCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkFields() {
        BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }

}
