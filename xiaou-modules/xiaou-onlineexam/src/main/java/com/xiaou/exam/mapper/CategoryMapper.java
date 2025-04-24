package com.xiaou.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.exam.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}