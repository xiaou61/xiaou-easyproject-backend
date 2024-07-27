package com.xiaou.xiaoueasyprojectbackend.module.support.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.CommentDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ReplyDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.Comment;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.CommentVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.ReviewVO;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    List<CommentDTO> listComments();

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();


    void updateCommentsReview(ReviewVO reviewVO);

}