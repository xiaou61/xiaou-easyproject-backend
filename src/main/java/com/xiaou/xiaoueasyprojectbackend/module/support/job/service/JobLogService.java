package com.xiaou.xiaoueasyprojectbackend.module.support.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.JobLogDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.PageResultDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.JobLog;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobLogSearchVO;

import java.util.List;

public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}