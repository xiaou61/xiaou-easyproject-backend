package com.xiaou.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 根据试题id获取单题详情
     *
     * @param id 试题id
     * @return 结果集
     */
    QuestionVO selectSingle(Integer id);

}
