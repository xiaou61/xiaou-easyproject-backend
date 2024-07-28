package com.xiaou.xiaoueasyprojectbackend.module.support.comment.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.CommentDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ReplyDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.CommentService;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.CommentVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.ReviewVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xiaou61
 * @Date 2024/7/27 13:41
 * @Description 评论功能实现
 */

@RestController
@RequestMapping("/v1/comment")
@Tag(name = "评论功能实现V1", description = "评论功能实现")
public class CommentControllerV1 {

    @Resource
    private CommentService commentService;


    @Operation(description = "添加评论")
    @PostMapping("/comments/save")
    public ResultVO<?> saveComment(@Valid @RequestBody CommentVO commentVO) {
        commentService.saveComment(commentVO);
        return ResultVO.ok();
    }

    @Operation(description = "获取评论")
    @GetMapping("/comments")
    public ResultVO<List<CommentDTO>> getComments() {
        return ResultVO.ok(commentService.listComments());
    }

    @Operation(description = "根据commentId获取回复")
    @GetMapping("/comments/{commentId}/replies")
    public ResultVO<List<ReplyDTO>> listRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        return ResultVO.ok(commentService.listRepliesByCommentId(commentId));
    }

    @Operation(description = "获取前六个评论")
    @GetMapping("/comments/topSix")
    public ResultVO<List<CommentDTO>> listTopSixComments() {
        return ResultVO.ok(commentService.listTopSixComments());
    }



    @Operation(description = "审核评论")
    @PutMapping("/admin/comments/review")
    public ResultVO<?> updateCommentsReview(@Valid @RequestBody ReviewVO reviewVO) {
        commentService.updateCommentsReview(reviewVO);
        return ResultVO.ok();
    }

    @Operation(description = "删除评论")
    @DeleteMapping("/admin/comments")
    public ResultVO<?> deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return ResultVO.ok();
    }

}
