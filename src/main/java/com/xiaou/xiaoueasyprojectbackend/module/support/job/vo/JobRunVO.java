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
public class JobRunVO {

    @Schema(description="任务id" )
    private Integer id;

    @Schema(description="任务组别")
    private String jobGroup;
}
