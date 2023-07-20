package com.xiaou.xiaoueasyprojectbackend.module.support.question.form;

import com.xiaou.xiaoueasyprojectbackend.module.support.question.group.QuestionGroup;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity.Option;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionFrom {

    private Integer id;

    /**
     * 试题类型
     */
    @NotNull(message = "试题类型(quType)不能为空", groups = QuestionGroup.QuestionAddGroup.class)
    @Min(value = 1, message = "试题类型(quType)只能是：1单选2多选3判断4简答", groups = QuestionGroup.QuestionAddGroup.class)
    @Max(value = 4, message = "试题类型(quType)只能是：1单选2多选3判断4简答", groups = QuestionGroup.QuestionAddGroup.class)
    private Integer quType;

    /**
     * 试题图片
     */
    private String image;
    private String analysis;

    /**
     * 题干
     */
    @NotBlank(message = "题干(content)不能为空", groups = QuestionGroup.QuestionAddGroup.class)
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @NotNull(message = "题库id(repoId)不能为空", groups = QuestionGroup.QuestionAddGroup.class)
    private Integer repoId;

    /**
     * 选项列表
     */
//    @NotNull(message = "选项列表(options)不能为空", groups = QuestionGroup.QuestionAddGroup.class)
//    @Size(min = 2, message = "选项列表(options)至少需要两个选项", groups = QuestionGroup.QuestionAddGroup.class)
//    @Valid
    private List<Option> options;






}