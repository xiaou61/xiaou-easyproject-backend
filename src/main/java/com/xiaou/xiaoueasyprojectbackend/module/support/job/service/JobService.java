package com.xiaou.xiaoueasyprojectbackend.module.support.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.JobDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.PageResultDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.Job;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobRunVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobSearchVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobStatusVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobVO;

import java.util.List;

public interface JobService extends IService<Job> {

    void saveJob(JobVO jobVO);

    void updateJob(JobVO jobVO);

    void deleteJobs(List<Integer> tagIds);

    JobDTO getJobById(Integer jobId);

    PageResultDTO<JobDTO> listJobs(JobSearchVO jobSearchVO);

    void updateJobStatus(JobStatusVO jobStatusVO);

    void runJob(JobRunVO jobRunVO);

    List<String> listJobGroups();

}