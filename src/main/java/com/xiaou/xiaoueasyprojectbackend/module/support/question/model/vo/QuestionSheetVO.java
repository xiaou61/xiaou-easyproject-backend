package com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo;

import lombok.Data;

@Data
public class QuestionSheetVO {
    private Integer quId;
    private Integer quType;
    private Integer repoId;
    private Integer exercised;
    private Integer isRight;
}
