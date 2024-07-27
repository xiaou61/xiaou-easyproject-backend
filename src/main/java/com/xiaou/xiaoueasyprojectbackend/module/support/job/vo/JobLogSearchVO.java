package com.xiaou.xiaoueasyprojectbackend.module.support.job.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobLogSearchVO {

    @Schema(description = "任务Id")
    private Integer jobId;

    @Schema(description = "任务名称")
    private String jobName;

    @Schema(description = "任务的组别")
    private String jobGroup;

    @Schema(description = "任务状态")
    private Integer status;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;
}
