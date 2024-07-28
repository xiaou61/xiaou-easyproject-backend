package com.xiaou.xiaoueasyprojectbackend.module.support.notice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity.Notice;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity.PageParam;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.resp.ServerResponseEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/notice")
@Tag(name = "公告管理V1", description = "NoticeControllerV1")
public class NoticeControllerV1 {

    @Resource
    private NoticeService noticeService;

    /**
     * 分页查询
     *
     * @param page   分页对象
     * @param notice 公告管理
     * @return 分页数据
     */
    @GetMapping("/page")
    public ServerResponseEntity<IPage<Notice>> getNoticePage(PageParam<Notice> page, Notice notice) {
        IPage<Notice> noticePage = noticeService.page(page, new LambdaQueryWrapper<Notice>()
                .eq(notice.getStatus() != null, Notice::getStatus, notice.getStatus())
                .eq(notice.getIsTop() != null, Notice::getIsTop, notice.getIsTop())
                .like(notice.getTitle() != null, Notice::getTitle, notice.getTitle()).orderByDesc(Notice::getUpdateTime));
        return ServerResponseEntity.success(noticePage);
    }


    /**
     * 通过id查询公告管理
     *
     * @param id id
     * @return 单个数据
     */
    @GetMapping("/info/{id}")
    public ServerResponseEntity<Notice> getById(@PathVariable("id") Long id) {
        return ServerResponseEntity.success(noticeService.getById(id));
    }

    /**
     * 新增公告管理
     *
     * @param notice 公告管理
     * @return 是否新增成功
     */
    @PostMapping
    public ServerResponseEntity<Boolean> save(@RequestBody @Valid Notice notice) {
        //这里可以根据实际情况去修改
        notice.setShopId(1L);
        if (notice.getStatus() == 1) {
            notice.setPublishTime(new Date());
        }
        notice.setUpdateTime(new Date());
        noticeService.removeNoticeList();
        return ServerResponseEntity.success(noticeService.save(notice));
    }

    /**
     * 修改公告管理
     *
     * @param notice 公告管理
     * @return 是否修改成功
     */
    @PutMapping
    public ServerResponseEntity<Boolean> updateById(@RequestBody @Valid Notice notice) {
        Notice oldNotice = noticeService.getById(notice.getId());
        if (oldNotice.getStatus() == 0 && notice.getStatus() == 1) {
            notice.setPublishTime(new Date());
        }
        notice.setUpdateTime(new Date());
        noticeService.removeNoticeList();
        noticeService.removeNoticeById(notice.getId());
        return ServerResponseEntity.success(noticeService.updateById(notice));
    }

    /**
     * 通过id删除公告管理
     *
     * @param id id
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public ServerResponseEntity<Boolean> removeById(@PathVariable Long id) {
        noticeService.removeNoticeList();
        noticeService.removeNoticeById(id);
        return ServerResponseEntity.success(noticeService.removeById(id));
    }
}
