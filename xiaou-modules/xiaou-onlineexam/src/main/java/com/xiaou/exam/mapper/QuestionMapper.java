package com.xiaou.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.exam.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
