package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.controller;



import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.ResponseDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.BulkOperationCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeAddCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeUpdateCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.dto.NoticeDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.dto.PageDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.query.NoticeQuery;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.service.NoticeApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/30 14:47
 * @Description: 公告功能实现
 */
@Tag(name = "公告API V2", description = "公告相关的增删查改")
@RestController
@RequestMapping("/system/notices")
@Validated
@RequiredArgsConstructor
public class SysNoticeControllerV2 {


    @Resource
    private NoticeApplicationService noticeApplicationService;

    /**
     * 获取通知公告列表
     */
    @Operation(summary = "公告列表")
    @GetMapping
    public ResponseDTO<PageDTO<NoticeDTO>> list(NoticeQuery query) {
        PageDTO<NoticeDTO> pageDTO = noticeApplicationService.getNoticeList(query);
        return ResponseDTO.ok(pageDTO);
    }


    /**
     * 根据通知公告编号获取详细信息
     */
    @Operation(summary = "公告详情")
    @GetMapping(value = "/{noticeId}")
    public ResponseDTO<NoticeDTO> getInfo(@PathVariable @NotNull @Positive Long noticeId) {
        return ResponseDTO.ok(noticeApplicationService.getNoticeInfo(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Operation(summary = "添加公告")
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody NoticeAddCommand addCommand) {
        noticeApplicationService.addNotice(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改通知公告
     */
    @Operation(summary = "修改公告")
    @PutMapping("/{noticeId}")
    public ResponseDTO<Void> edit(@PathVariable Long noticeId, @RequestBody NoticeUpdateCommand updateCommand) {
        updateCommand.setNoticeId(noticeId);
        noticeApplicationService.updateNotice(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除通知公告
     */
    @Operation(summary = "删除公告")
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam List<Integer> noticeIds) {
        noticeApplicationService.deleteNotice(new BulkOperationCommand<>(noticeIds));
        return ResponseDTO.ok();
    }


}
