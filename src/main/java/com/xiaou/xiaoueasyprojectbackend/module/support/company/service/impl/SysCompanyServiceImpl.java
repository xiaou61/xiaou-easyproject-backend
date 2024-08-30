package com.xiaou.xiaoueasyprojectbackend.module.support.company.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xiaou.xiaoueasyprojectbackend.module.support.company.entity.SysCompany;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.mapper.SysCompanyMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.search.SysCompanySearch;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.service.SysCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 公司表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysCompanyServiceImpl extends ServiceImpl<SysCompanyMapper, SysCompany>
        implements SysCompanyService {

    private final SysCompanyMapper sysCompanyMapper;

    @Override
    public List<SysCompany> query(SysCompanySearch search) {

        List<SysCompany> result = sysCompanyMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search) {

        page = sysCompanyMapper.queryPage(page, search);
        log.info("support------>{}", page);

        return page;
    }

    @Override
    public SysCompany get(String id) {
        SysCompany result = getById(id);

        log.info("result-->{}", result);

        return result;
    }

    @Override
    public boolean insert(SysCompany sysCompany) {
        sysCompany.setCreateTime(new Date());
        sysCompany.setUpdateTime(new Date());
        return save(sysCompany);
    }

    @Override
    public boolean update(SysCompany sysCompany) {
        sysCompany.setCreateTime(new Date());
        sysCompany.setUpdateTime(new Date());
        return updateById(sysCompany);
    }

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }
}