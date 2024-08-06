package com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.entity.SysMenu;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.search.SysMenuSearch;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

    SysMenu get(String id);

    SysMenu insert(SysMenu sysMenu);

    SysMenu update(SysMenu sysMenu);

    boolean delete(String id);
}