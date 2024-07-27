package com.xiaou.xiaoueasyprojectbackend.module.support.job.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.JobDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.PageResultDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.service.JobService;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xiaou61
 * @Date 2024/7/27 11:37
 * @Description: 定时任务实现 用到了俩个表 定时任务存放在{@link com.xiaou.xiaoueasyprojectbackend.module.support.job.quartz.AuroraQuartz}
 */
@RestController
@RequestMapping("/v1/job")
public class JobControllerV1 {

    @Resource
    private JobService jobService;

    /**
     * @Author xiaou61
     * @Date 2024/7/27 上午11:12
     * @Description 添加定时任务
     * {
     *   "id": 81,
     *   "jobName": "测试",
     *   "jobGroup": "默认",
     *   "invokeTarget": "auroraQuartz.test",
     *   "cronExpression": "0/1 * * * * ?",
     *   "misfirePolicy": 1,
     *   "concurrent": 1,
     *   "status": 1,
     *   "remark": "测试执行"
     * }
     * @Since version 1.0
     */
    @PostMapping("/jobs")
    public ResultVO<?> saveJob(@RequestBody JobVO jobVO) {
        jobService.saveJob(jobVO);
        return ResultVO.ok();
    }


    /**
     * @Author xiaou61
     * @Date 2024/7/27 上午11:13
     * @Description 更新定时任务
     * @Since version 1.0
     */
    @PutMapping("/jobs")
    public ResultVO<?> updateJob(@RequestBody JobVO jobVO) {
        jobService.updateJob(jobVO);
        return ResultVO.ok();
    }



    /**
     * @Author xiaou61
     * @Date 2024/7/27 上午11:13
     * @Description 删除定时任务
     * @Since version 1.0
     */
    @DeleteMapping("/jobs")
    public ResultVO<?> deleteJobById(@RequestBody List<Integer> jobIds) {
        jobService.deleteJobs(jobIds);
        return ResultVO.ok();
    }


    /**
     * 根据id获得任务
     * @param jobId
     * @return
     */
    @GetMapping("/jobs/{id}")
    public ResultVO<JobDTO> getJobById(@PathVariable("id") Integer jobId) {
        return ResultVO.ok(jobService.getJobById(jobId));
    }


    /**
     * 获得任务列表
     * @param jobSearchVO
     * @return
     */
    @GetMapping("/jobs")
    public ResultVO<PageResultDTO<JobDTO>> listJobs(JobSearchVO jobSearchVO) {
        return ResultVO.ok(jobService.listJobs(jobSearchVO));
    }


    /**
     * 更改任务的状态
     * @param jobStatusVO
     * @return
     */
    @PutMapping("/jobs/status")
    public ResultVO<?> updateJobStatus(@RequestBody JobStatusVO jobStatusVO) {
        jobService.updateJobStatus(jobStatusVO);
        return ResultVO.ok();
    }

    /**
     *  执行某个任务
     * @param jobRunVO
     * @return
     */
    @PutMapping("/jobs/run")
    public ResultVO<?> runJob(@RequestBody JobRunVO jobRunVO) {
        jobService.runJob(jobRunVO);
        return ResultVO.ok();
    }

    /**
     * 获取所有job分组
     * @return
     */
    @GetMapping("/jobs/jobGroups")
    public ResultVO<List<String>> listJobGroup() {
        return ResultVO.ok(jobService.listJobGroups());
    }
}
