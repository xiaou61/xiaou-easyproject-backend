package com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.CommentDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ReplyDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.Comment;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.mapper.CommentMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.CommentService;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.util.HTMLUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.CommentVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.ReviewVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.dto.PageResultDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.job.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    private static final Integer TRUE = 1;


    @Override
    public void saveComment(CommentVO commentVO) {
        commentVO.setCommentContent(HTMLUtil.filter(commentVO.getCommentContent()));
        Comment comment = Comment.builder()
                .userId(1) //这里可以根据你实际开发的来写
                .replyUserId(commentVO.getReplyUserId())
                .topicId(commentVO.getTopicId())
                .commentContent(commentVO.getCommentContent())
                .parentId(commentVO.getParentId())
                .type(commentVO.getType())
                .isReview(0) //初始未审核
                .build();
        commentMapper.insert(comment);

    }

    @Override
    public List<CommentDTO> listComments() {
        return commentMapper.listComments();
    }

    @Override
    public List<ReplyDTO> listRepliesByCommentId(Integer commentId) {
        return commentMapper.listReplies(Collections.singletonList(commentId));
    }

    @Override
    public List<CommentDTO> listTopSixComments() {
        return commentMapper.listTopSixComments();
    }


    @Override
    public void updateCommentsReview(ReviewVO reviewVO) {
        List<Comment> comments = reviewVO.getIds().stream().map(item -> Comment.builder()
                        .id(item)
                        .isReview(reviewVO.getIsReview())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(comments);
    }


}


