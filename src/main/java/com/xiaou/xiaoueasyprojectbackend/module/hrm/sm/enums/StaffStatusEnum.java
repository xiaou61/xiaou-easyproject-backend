package com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.enums;

import lombok.Getter;

/**
 * 员工状态
 *
 * @author 施子安
 * @date 2024年7月18日 14点14分
 */
@Getter
public enum StaffStatusEnum {
    //0-备选，1-面试，2-面试通过，3-入职申请，4-入职通过
    /**
     * 备选
     */
    ALTERNATE(0L,"备选"),
    INTERVIEW(1L,"面试"),
    INTERVIEW_PASS(2L,"面试通过"),
    ENTRY_APPLICATION(3L,"入职申请"),
    PASS_ADMISSION(4L,"入职通过"),
    INTERVIEW_FAIL(5L,"面试失败"),
    ;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态数值
     */
    private Long code;

    StaffStatusEnum(Long code, String remark) {
        this.remark = remark;
        this.code = code;
    }
}
