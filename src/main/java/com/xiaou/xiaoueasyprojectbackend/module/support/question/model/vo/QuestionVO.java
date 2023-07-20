package com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity.Option;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionVO {
    private Integer id;
    private String content;
    private Integer repoId;
    private  String image;
    private String repoTitle;
    private Integer quType;
    private String analysis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    private List<Option> options;
}