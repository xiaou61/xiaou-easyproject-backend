package com.xiaou.xiaoueasyprojectbackend.module.support.company.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.entity.SysCompany;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.search.SysCompanySearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCompanyMapper extends BaseMapper<SysCompany> {

    List<SysCompany> query(SysCompanySearch search);

    IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);
}