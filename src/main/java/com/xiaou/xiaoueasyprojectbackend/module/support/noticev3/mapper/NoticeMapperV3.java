package com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NoticeMapperV3 {

    /**
      * 新增
    */
    int insert(Notice notice);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Notice notice);

    /**
      * 根据ID查询
    */
    Notice selectById(Integer id);

    /**
      * 查询所有
    */
    List<Notice> selectAll(Notice notice);

}