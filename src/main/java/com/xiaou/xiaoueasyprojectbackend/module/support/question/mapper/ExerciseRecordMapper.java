package com.xiaou.xiaoueasyprojectbackend.module.support.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity.ExerciseRecord;

import java.util.List;

public interface ExerciseRecordMapper extends BaseMapper<ExerciseRecord> {

    /**
     * 删除用户练习作答记录
     * @param userIds 用户id列表
     * @return 影响记录数
     */
    Integer deleteByUserIds(List<Integer> userIds);
}