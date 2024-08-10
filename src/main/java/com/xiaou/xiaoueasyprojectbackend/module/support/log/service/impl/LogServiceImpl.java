package com.xiaou.xiaoueasyprojectbackend.module.support.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.ip.utils.IpUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.data.PageData;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.data.ReturnPageData;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.dto.LogDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.entity.Log;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.mapper.LogMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.query.LogQuery;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.service.LogService;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.utils.PageUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.vo.LogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志 服务实现类
 *
 * @author xiaohai
 * @since 2023-03-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public Integer add(LogVo vo) {
        Log log = new Log();
        BeanUtils.copyProperties(vo, log);
        return baseMapper.insert(log);
    }

    @Override
    public Integer delete(Long[] ids) {
        for (Long id : ids) {
            baseMapper.deleteById(id);
        }
        return ids.length;
    }

    @Override
    public Integer deleteAll() {
        return baseMapper.truncateTable();
    }


    @Override
    public Log findById(Long id) {
        Log log = baseMapper.selectById(id);
        log.setOperIp(log.getOperIp() + "(" + IpUtils.getIp2region(log.getOperIp()) + ")");
        return log;
    }

    @Override
    public ReturnPageData<LogDto> findListByPage(LogQuery query) {
        Log log = new Log();
        BeanUtils.copyProperties(query, log);
        IPage<Log> wherePage = new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize());
        IPage<Log> iPage = baseMapper.selectPage(wherePage, Wrappers.query(log).orderByDesc("created_time"));
        List<LogDto> list = new ArrayList<>();
        for (Log logs : iPage.getRecords()) {
            LogDto logDto = new LogDto();
            BeanUtils.copyProperties(logs, logDto);
            logDto.setOperIp(logs.getOperIp() + "(" + IpUtils.getIp2region(logs.getOperIp()) + ")");
            list.add(logDto);
        }
        PageData pageData = new PageData();
        BeanUtils.copyProperties(iPage, pageData);
        return ReturnPageData.fillingData(pageData, list);
    }

}
