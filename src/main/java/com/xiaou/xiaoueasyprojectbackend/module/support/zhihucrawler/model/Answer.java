package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述：回答
 *
 * @author zwl
 * @since 2022/5/25 14:14
 */
@Data
@Accessors(chain = true)
public class Answer {

    /**
     * 点赞数
     */
    Integer voteupCount;
    /**
     * 评论数
     */
    Integer commentCount;
    /**
     * 回答id
     */
    Long answerId;
    /**
     * 问题id
     */
    Long questionId;
    /**
     * 问题标题
     */
    String title;
    /**
     * 作者
     */
    String authorName;
    /**
     * 回答url
     */
    String answerUrl;
}
