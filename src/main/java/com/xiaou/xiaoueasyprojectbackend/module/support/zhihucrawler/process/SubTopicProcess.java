package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.process;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model.Topic;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.CrawlerUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.TopicTree;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternProcessor;
import us.codecraft.webmagic.selector.Json;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 描述：子话题
 *
 * @author zwl
 * @since 2022/5/24 11:19
 */
@Slf4j
public class SubTopicProcess extends PatternProcessor {
    /**
     * @param pattern url pattern to handle
     */
    public SubTopicProcess(String pattern) {
        super(pattern);
    }

    @Override
    public MatchOther processPage(Page page) {
        // todo 子话题爬取分两种情况，一种是延续翻页，一种是爬取子话题
        String body = URLDecoder.decode(new String(page.getRequest().getRequestBody().getBody()), Charset.defaultCharset());
        Long parentTopicId = Long.parseLong(StrUtil.subBetween(body, "id\":", ","));
        int offset = Integer.parseInt(StrUtil.subBetween(body, "offset\":", ","));

        // 通过msg判断翻页是否继续 为空代表没有数据了 结束翻页 否则继续添加翻页请求
        Json json = page.getJson();
        if (json == null) {
            return MatchOther.NO;
        }
        JSONArray msg = JSON.parseObject(json.toString()).getJSONArray("msg");
        if (msg.isEmpty()) {
            return MatchOther.NO;
        }

        log.info("添加话题翻页,topic:{},offset:{}", parentTopicId, offset);
        page.addTargetRequest(CrawlerUtils.assemblyBody(parentTopicId, offset + 20));

        // 解析
        CopyOnWriteArrayList<Topic> topics = msg.parallelStream().map(selectable -> {
            String topicId = StrUtil.subBetween(selectable.toString(), "topic/", "\"");
            String topicName = StrUtil.subBetween(selectable.toString(), "<strong>", "</strong>");

            return new Topic().setTopicId(Long.valueOf(topicId)).setTopicName(topicName).setParentId(parentTopicId);
        }).collect(Collectors.toCollection(CopyOnWriteArrayList::new));

        // 添加topic节点
        TopicTree.putNodeByParentId(topics, parentTopicId);

        // todo 只有当offset为0时代表第一次请求 这时候加载当前话题的子话题
        if (offset == 0) {
            log.info("添加话题【{}】的子话题", parentTopicId);
            // 添加子话题爬取
            topics.forEach(topic -> page.addTargetRequest(CrawlerUtils.assemblyBody(topic.getTopicId(), 0)));
        }

        return MatchOther.YES;
    }

    @Override
    public MatchOther processResult(ResultItems resultItems, Task task) {
        return null;
    }
}
