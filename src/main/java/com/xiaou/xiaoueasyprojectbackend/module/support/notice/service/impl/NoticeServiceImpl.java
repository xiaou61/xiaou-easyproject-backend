

package com.xiaou.xiaoueasyprojectbackend.module.support.notice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiaou.xiaoueasyprojectbackend.module.support.notice.dto.NoticeDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity.Notice;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.mapper.NoticeMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.service.NoticeService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告管理
 *

 * @date 2019-04-18 21:21:40
 */
@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice() {
        return noticeMapper.selectList(new LambdaQueryWrapper<Notice>()
                .eq(Notice::getStatus, 1)
                .eq(Notice::getIsTop, 1)
                .orderByDesc(Notice::getPublishTime));
    }

    @Override
    public void removeNoticeList() {
    }

    @Override
    public Page<NoticeDto> pageNotice(Page<NoticeDto> page) {
        return noticeMapper.pageNotice(page);
    }

    @Override
    @Cacheable(cacheNames = "notice", key = "#noticeId")
    public Notice getNoticeById(Long noticeId) {
        return noticeMapper.selectById(noticeId);
    }

    @Override
    @CacheEvict(cacheNames = "notice", key = "#noticeId")
    public void removeNoticeById(Long noticeId) {
    }


}
