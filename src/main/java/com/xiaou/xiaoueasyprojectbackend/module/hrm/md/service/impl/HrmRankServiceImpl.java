package com.xiaou.xiaoueasyprojectbackend.module.hrm.md.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain.HrmRank;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.mapper.HrmRankMapper;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.service.IHrmRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 职级管理Service业务层处理
 *
 * @author t3rik
 * @date 2024-07-23
 */
@Service
public class HrmRankServiceImpl  extends ServiceImpl<HrmRankMapper, HrmRank>  implements IHrmRankService
{
    @Autowired
    private HrmRankMapper hrmRankMapper;

}
