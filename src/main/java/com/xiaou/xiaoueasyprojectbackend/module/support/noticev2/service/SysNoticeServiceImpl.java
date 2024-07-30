package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.SysNoticeEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeEntity> implements SysNoticeService {

    @Override
    public Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page, Wrapper<SysNoticeEntity> queryWrapper) {
        return this.baseMapper.getNoticeList(page, queryWrapper);
    }

}
