package com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.entity.SysMenu;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.search.SysMenuSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);
}