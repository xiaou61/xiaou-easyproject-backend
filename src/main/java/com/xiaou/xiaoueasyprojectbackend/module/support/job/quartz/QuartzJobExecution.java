package com.xiaou.xiaoueasyprojectbackend.module.support.job.quartz;


import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.Job;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
