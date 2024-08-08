package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.process;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternProcessor;
/**
 * 描述：爬取知乎文章回答 （暂不实现）
 *
 * @author zwl
 * @since 2022/5/23 17:03
 */
public class AnswerPageProcess extends PatternProcessor {
  /**
   * @param pattern url pattern to handle
   */
  public AnswerPageProcess(String pattern) {
    super(pattern);
  }

  @Override
  public MatchOther processPage(Page page) {
    return null;
  }

  @Override
  public MatchOther processResult(ResultItems resultItems, Task task) {
    return null;
  }
}
