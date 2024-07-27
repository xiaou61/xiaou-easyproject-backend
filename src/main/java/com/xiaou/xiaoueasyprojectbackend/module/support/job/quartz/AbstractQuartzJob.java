package com.xiaou.xiaoueasyprojectbackend.module.support.job.quartz;


import cn.hutool.extra.spring.SpringUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.constants.ScheduleConstant;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.Job;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.JobLog;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.mapper.JobLogMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.util.ExceptionUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

import static com.xiaou.xiaoueasyprojectbackend.module.support.job.constants.CommonConstant.ONE;
import static com.xiaou.xiaoueasyprojectbackend.module.support.job.constants.CommonConstant.ZERO;


public abstract class AbstractQuartzJob implements org.quartz.Job {

    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    private static final ThreadLocal<Date> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstant.TASK_PROPERTIES), job);
        try {
            before(context, job);
            doExecute(context, job);
            after(context, job, null);
        } catch (Exception e) {
            log.error("任务执行异常:", e);
            after(context, job, e);
        }
    }

    protected void before(JobExecutionContext context, Job job) {
        THREAD_LOCAL.set(new Date());
    }

    protected void after(JobExecutionContext context, Job job, Exception e) {
        Date startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        final JobLog jobLog = new JobLog();
        jobLog.setJobId(job.getId());
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setStartTime(startTime);
        jobLog.setEndTime(new Date());
        long runMs = jobLog.getEndTime().getTime() - jobLog.getStartTime().getTime();
        jobLog.setJobMessage(jobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            jobLog.setStatus(ZERO);
            jobLog.setExceptionInfo(ExceptionUtil.getTrace(e));
        } else {
            jobLog.setStatus(ONE);
        }
        SpringUtil.getBean(JobLogMapper.class).insert(jobLog);
    }

    protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}
