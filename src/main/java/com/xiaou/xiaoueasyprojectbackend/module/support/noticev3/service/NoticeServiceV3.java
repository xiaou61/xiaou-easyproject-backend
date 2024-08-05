package com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.service;

import cn.hutool.core.date.DateUtil;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.entity.Notice;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.mapper.NoticeMapperV3;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class NoticeServiceV3 {

    @Resource
    private NoticeMapperV3 noticeMapperV3;

    /**
     * 新增
     */
    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        //这里需要自己根据实际情况
        notice.setUser("xiaou61");
        noticeMapperV3.insert(notice);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        noticeMapperV3.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            noticeMapperV3.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Notice notice) {
        noticeMapperV3.updateById(notice);
    }

    /**
     * 根据ID查询
     */
    public Notice selectById(Integer id) {
        return noticeMapperV3.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Notice> selectAll(Notice notice) {
        return noticeMapperV3.selectAll(notice);
    }

    /**
     * 分页查询
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapperV3.selectAll(notice);
        return PageInfo.of(list);
    }

}