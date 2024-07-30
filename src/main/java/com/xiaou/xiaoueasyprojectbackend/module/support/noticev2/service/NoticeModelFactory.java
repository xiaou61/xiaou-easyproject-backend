package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.service;


import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.SysNoticeEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.model.NoticeModel;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 公告模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class NoticeModelFactory {

    @Resource
    private SysNoticeService noticeService;

    public NoticeModel loadById(Long noticeId) {
        SysNoticeEntity byId = noticeService.getById(noticeId);

        if (byId == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return new NoticeModel(byId);
    }

    public NoticeModel create() {
        return new NoticeModel();
    }


}
