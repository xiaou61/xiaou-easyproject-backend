package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.core.Param;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.core.Select;

public interface SysUserDao {

    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id") Long id);

}
