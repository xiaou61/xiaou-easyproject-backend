package com.xiaou.xiaoueasyprojectbackend.module.support.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.SysUser;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.query.SysUserQuery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapperAuth extends BaseMapper<SysUser> {

    List<SysUser> selectUserByCondition(SysUserQuery sysUserQuery);
}