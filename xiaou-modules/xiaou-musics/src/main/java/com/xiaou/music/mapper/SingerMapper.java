package com.xiaou.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.music.model.domain.Singer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingerMapper extends BaseMapper<Singer> {
}
