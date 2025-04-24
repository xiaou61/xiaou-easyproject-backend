package com.xiaou.exam.model.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CategoryVO {

    private Integer id;

    private String name;


    private Integer parentId;


    private Integer sort;


    private Date createTime;


    private List<CategoryVO> children;
}