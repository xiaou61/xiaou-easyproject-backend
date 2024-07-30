package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.service;



import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.BulkOperationCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeAddCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command.NoticeUpdateCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.dto.NoticeDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.dto.PageDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.SysNoticeEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.model.NoticeModel;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.query.NoticeQuery;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class NoticeApplicationService {


    @Resource
    private final SysNoticeService noticeService;

    @Resource
    private final NoticeModelFactory noticeModelFactory;

    public PageDTO<NoticeDTO> getNoticeList(NoticeQuery query) {
        Page<SysNoticeEntity> page = noticeService.getNoticeList(query.toPage(), query.toQueryWrapper());
        List<NoticeDTO> records = page.getRecords().stream().map(NoticeDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }


    public NoticeDTO getNoticeInfo(Long id) {
        NoticeModel noticeModel = noticeModelFactory.loadById(id);
        return new NoticeDTO(noticeModel);
    }


    public void addNotice(NoticeAddCommand addCommand) {
        NoticeModel noticeModel = noticeModelFactory.create();
        noticeModel.loadAddCommand(addCommand);
        //todo 这里需要自己对接用户信息
        noticeModel.setCreatorId(1L);
        noticeModel.setCreateTime(new Date());
        noticeModel.checkFields();


        noticeModel.insert();
    }


    public void updateNotice(NoticeUpdateCommand updateCommand) {
        NoticeModel noticeModel = noticeModelFactory.loadById(updateCommand.getNoticeId());
        noticeModel.loadUpdateCommand(updateCommand);

        noticeModel.checkFields();

        noticeModel.updateById();
    }

    public void deleteNotice(BulkOperationCommand<Integer> deleteCommand) {
        noticeService.removeBatchByIds(deleteCommand.getIds());
    }




}
