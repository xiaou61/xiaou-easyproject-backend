package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述：话题树形模型
 *
 * @author zwl
 * @since 2022/5/24 10:47
 */
@Data
@Accessors(chain = true)
public class Topic {

  private Long topicId;
  private Long parentId;
  private String topicName;
  private Long followers;
  private CopyOnWriteArrayList<Topic> subTopics;
}
