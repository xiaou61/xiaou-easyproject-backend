
package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;

import lombok.Data;

import java.util.List;


@Data
public class Target {

    private Long answerCount;
    private Author author;
    private List<Long> boundTopicIds;
    private Long commentCount;
    private Long created;
    private String excerpt;
    private Long followerCount;
    private Long id;
    private Boolean isFollowing;
    private String title;
    private String type;
    private String url;

}
