package com.xiaou.xiaoueasyprojectbackend.module.support.comment.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.CommentDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ReplyDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.Comment;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> listComments();

    List<ReplyDTO> listReplies(@Param("commentIds") List<Integer> commentIdList);

    List<CommentDTO> listTopSixComments();


}
