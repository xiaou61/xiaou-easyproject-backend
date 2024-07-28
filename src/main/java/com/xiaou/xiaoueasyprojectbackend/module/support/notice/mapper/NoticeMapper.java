package com.xiaou.xiaoueasyprojectbackend.module.support.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.dto.NoticeDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity.Notice;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 公告管理
 *
 * @author hzm
 * @date 2019-04-18 21:21:40
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 分页获取公布的公告
     * @param page
     * @return
     */
    Page<NoticeDto> pageNotice(Page<NoticeDto> page);
}
