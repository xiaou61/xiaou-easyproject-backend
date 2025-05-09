package com.xiaou.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.exam.model.entity.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper extends BaseMapper<Option> {
    /**
     * 批量添加选项
     *
     * @param options 选型列表
     * @return 影响数据库内容的记录数
     */
    Integer insertBatch(List<Option> options);

    /**
     * 根据试题id获取所有选项
     *
     * @param id 试题id
     * @return 结果集
     */
    List<Option> selectAllByQuestionId(Integer id);
}
