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
public class JobVO {

    @Schema(description = "任务id")
    private Integer id;

    @Schema(description = "任务名称")
    private String jobName;

    @Schema(description = "任务组名")
    private String jobGroup;

    @Schema(description = "调用目标字符串")
    private String invokeTarget;

    @Schema(description = "cron执行表达式")
    private String cronExpression;

    @Schema(description = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    private Integer misfirePolicy;

    @Schema(description = "是否并发执行（0允许 1禁止）")
    private Integer concurrent;

    @Schema(description = "状态（0正常 1暂停）")
    private Integer status;

    @Schema(description = "备注信息")
    private String remark;
}
