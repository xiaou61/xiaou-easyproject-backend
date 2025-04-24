package com.xiaou.exam.model.req;


import com.xiaou.exam.model.entity.Option;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 试卷请求体
 */
@Data
public class QuestionFrom {

    private Integer id;

    /**
     * 试题类型
     */
    @NotNull(message = "试题类型(quType)不能为空")
    @Min(value = 1, message = "试题类型(quType)只能是：1单选2多选3判断4简答")
    @Max(value = 4, message = "试题类型(quType)只能是：1单选2多选3判断4简答")
    private Integer quType;

    /**
     * 试题图片
     */
    private String image;
    private String analysis;

    /**
     * 题干
     */
    @NotBlank(message = "题干(content)不能为空")
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 题库ID
     */
    @NotNull(message = "题库id(repoId)不能为空")
    private Integer repoId;

    /**
     * 选项列表
     */
    private List<Option> options;

}
