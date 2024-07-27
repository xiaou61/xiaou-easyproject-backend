package com.xiaou.xiaoueasyprojectbackend.module.support.job.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.JobDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.entity.Job;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.JobSearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper extends BaseMapper<Job> {

    Integer countJobs(@Param("jobSearchVO") JobSearchVO jobSearchVO);

    List<JobDTO> listJobs(@Param("current") Long current, @Param("size") Long size, @Param("jobSearchVO")JobSearchVO jobSearchVO);

    List<String> listJobGroups();

}
