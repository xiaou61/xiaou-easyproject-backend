package com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.domain.HrmStaff;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.vo.HrmStaffVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工花名册Mapper接口
 *
 * @author 施子安
 * @date 2024-07-11
 */
@Mapper
public interface HrmStaffMapper extends BaseMapper<HrmStaff> {


    List<HrmStaffVo> listTalents(HrmStaff hrmStaff);

    /**
     * 获取人才详情信息
     */
    HrmStaffVo getTalents(Long staffId);
}
