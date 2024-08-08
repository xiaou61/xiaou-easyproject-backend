package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author zwl
 * @since 2022/5/20 16:22
 */
@Data
public class HotTopVo {
  private List<Datum> data;
  private Long displayNum;
  private String freshText;
  private Paging paging;
}
