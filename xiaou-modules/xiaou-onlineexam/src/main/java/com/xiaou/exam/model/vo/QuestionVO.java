package com.xiaou.exam.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaou.exam.model.entity.Option;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionVO {
    private Integer id;
    // 题干
    private String content;
    // 题库ID
    private Integer repoId;
    // 图片
    private  String image;
    // 题库标题
    private String repoTitle;
    // 试题类型
    private Integer quType;
    private String analysis;
    // 创建试卷
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 选项列表
    private List<Option> options;
}
