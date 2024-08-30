package com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.domain.HrmStaff;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.vo.HrmStaffVo;


import java.util.List;

/**
 * 员工花名册Service接口
 *
 * @author 施子安
 * @date 2024-07-11
 */
public interface IHrmStaffService extends IService<HrmStaff> {

    /**
     * 员工审批流程
     * @param hrmStaff
     */
    void process(HrmStaff hrmStaff);

    /**
     * 人才登记列表查询
     * @param hrmStaff
     * @return
     */
    List<HrmStaffVo> listTalents(HrmStaff hrmStaff);

    /**
     * 获取人才详情信息
     */
    HrmStaffVo getTalents(Long staffId);
}
