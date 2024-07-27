package com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "评论")
public class CommentVO {

    @Parameter(description = "回复用户id")
    private Integer replyUserId;

    @Parameter(description = "主题id")
    private Integer topicId;

    @NotBlank(message = "评论内容不能为空")
    @Schema(description = "评论内容", required = true)
    private String commentContent;

    @Parameter(description = "评论父id")
    private Integer parentId;

    @NotNull(message = "评论类型不能为空")
    @Schema(description = "评论类型")
    private Integer type;
}
