package com.xiaou.xiaoueasyprojectbackend.module.support.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.entity.SysCompany;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.search.SysCompanySearch;

import java.util.List;

public interface SysCompanyService extends IService<SysCompany> {

    List<SysCompany> query(SysCompanySearch search);

    IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);

    SysCompany get(String id);

    boolean insert(SysCompany sysCompany);

    boolean update(SysCompany sysCompany);

    boolean delete(String id);
}