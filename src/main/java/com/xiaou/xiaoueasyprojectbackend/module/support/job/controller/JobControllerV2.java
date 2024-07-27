package com.xiaou.xiaoueasyprojectbackend.module.support.job.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.JobLogDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.PageResultDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.service.JobLogService;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobLogSearchVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/27 11:56
 * @Description: 定时任务日志模块
 */
@RestController
@RequestMapping("/v2/job")
public class JobControllerV2 {
    @Autowired
    private JobLogService jobLogService;


    @GetMapping("/admin/jobLogs")
    public ResultVO<PageResultDTO<JobLogDTO>> listJobLogs(JobLogSearchVO jobLogSearchVO) {
        return ResultVO.ok(jobLogService.listJobLogs(jobLogSearchVO));
    }


    @DeleteMapping("/admin/jobLogs")
    public ResultVO<?> deleteJobLogs(@RequestBody List<Integer> ids) {
        jobLogService.deleteJobLogs(ids);
        return ResultVO.ok();
    }

    @DeleteMapping("/admin/jobLogs/clean")
    public ResultVO<?> cleanJobLogs() {
        jobLogService.cleanJobLogs();
        return ResultVO.ok();
    }

    @GetMapping("/admin/jobLogs/jobGroups")
    public ResultVO<?> listJobLogGroups() {
        return ResultVO.ok(jobLogService.listJobLogGroups());
    }
}
