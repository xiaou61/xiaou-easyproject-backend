package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.process;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.google.common.collect.HashBasedTable;

import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.constant.ZhiHuConstant;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model.Answer;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model.Topic;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.CrawlerUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.TopicTree;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternProcessor;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 描述：爬取话题精华回答，点赞超过一万的回答
 *
 * @author zwl
 * @since 2022/5/23 16:53
 */
@Slf4j
public class TopAnswerProcess extends PatternProcessor {

    private final HashBasedTable<Long, Long, CopyOnWriteArrayList<Answer>> table = HashBasedTable.create();

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * @param pattern url pattern to handle
     */
    public TopAnswerProcess(String pattern) {
        super(pattern);
    }

    @Override
    public MatchOther processPage(Page page) {
        String url = page.getRequest().getUrl();
        Long topicId = Long.parseLong(StrUtil.subBetween(url, "topics/", "/"));
        int offset = Integer.parseInt(StrUtil.subAfter(url, "offset=", true));
        JSONObject object = JSON.parseObject(page.getJson().toString());
        JSONObject paging;

        if (object == null || object.getJSONObject("paging") == null) {
            String redirectUrl = Optional.ofNullable(object).map(jsonObject -> jsonObject.getJSONObject("error")).map(obj -> obj.getString("redirect").replace("\\", "")).orElse("");
            if (StrUtil.isNotEmpty(redirectUrl)) {
                log.warn("触发安全验证，redirectUrl:{},\n{}", redirectUrl, page.getJson());
                CrawlerUtils.close();
            }
            writeAnswerFile(topicId);
            return MatchOther.NO;
        }

        paging = object.getJSONObject("paging");
        double totals = paging.getDouble("totals");
        Boolean isEnd = paging.getBoolean("is_end");
        Boolean isStart = paging.getBoolean("is_start");

        if (isStart) {
            initAllRequest(topicId, totals);
        }

        // 解析话题答案
        Map<Long, CopyOnWriteArrayList<Answer>> map = object.getJSONArray("data").stream().filter(o -> {
            JSONObject obj = (JSONObject) o;
            JSONObject target = obj.getJSONObject("target");
            String type = target.getString("type");
            Integer voteupCount = target.getInteger("voteup_count");
            // 类型为回答并且点赞超过一万
            return Objects.equals(type, "answer") && voteupCount > 10000;
        }).map(o -> {
            JSONObject obj = (JSONObject) o;
            JSONObject target = obj.getJSONObject("target");
            JSONObject question = target.getJSONObject("question");
            JSONObject author = target.getJSONObject("author");
            String authorName = author.getString("name");
            Integer voteupCount = target.getInteger("voteup_count");
            Integer commentCount = target.getInteger("comment_count");
            Long answerId = target.getLong("id");
            Long questionId = question.getLong("id");
            String title = question.getString("title");

            return new Answer().setAuthorName(authorName).setAnswerId(answerId).setQuestionId(questionId).setVoteupCount(voteupCount).setCommentCount(commentCount).setTitle(title).setAnswerUrl(StrUtil.format(ZhiHuConstant.ANSWER_PAGE_URL, questionId, answerId));
        }).collect(Collectors.groupingBy(Answer::getQuestionId, Collectors.toCollection(CopyOnWriteArrayList::new)));

        lock.lock();
        try {
            map.forEach((qid, answers) -> {
                CopyOnWriteArrayList<Answer> arrayList = table.get(topicId, qid);
                if (arrayList == null) {
                    table.put(topicId, qid, answers);
                } else {
                    arrayList.addAll(answers);
                }

            });
        } catch (Exception e) {
            log.error("添加table异常:{}", e.getMessage());
        } finally {
            lock.unlock();
        }
        if (isEnd || offset == 1000) {
            writeAnswerFile(topicId);
            return MatchOther.NO;
        }

        return MatchOther.YES;
    }

    /**
     * 测试发现offset最多为一千，总计5万条回答，超出部分不会被爬取
     *
     * @param topicId
     * @param totals
     */
    private void initAllRequest(Long topicId, double totals) {
        int offset = 50;
        if (totals < offset) {
            return;
        }
        double pages = Math.ceil(totals / offset);
        if (totals > 1000) {
            pages = 20;
        }
        //总页数
        for (int i = 0; i < pages - 1; i++) {
            CrawlerUtils.addReqyest(ZhiHuConstant.ANSWER_URL.formatted(topicId, offset += 50));
        }
    }

    /**
     * 初始化话题精华回答文件
     *
     * @param topic
     */
    private void initAnswerFile(Topic topic) {
        // 初始化文件
        String fileName = ZhiHuConstant.TOPIC_ANSWER_FILE_NAME.formatted("answer", topic.getTopicName().replace("/", " "), topic.getTopicId(), "md");
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                String buffer = "# " + topic.getTopicName() + "\n" +
                        "关注人数：" + topic.getFollowers() + "\n";
                FileUtil.writeUtf8String(buffer, file);
            } catch (Exception ignore) {
            }
        }
    }


    /**
     * 写入回答到文件
     *
     * @param topicId
     */
    private void writeAnswerFile(Long topicId) {
        lock.lock();
        try {
            Map<Long, CopyOnWriteArrayList<Answer>> map = table.row(topicId);

            if (CollectionUtil.isEmpty(map)) {
                return;
            }
            Topic topic = TopicTree.getTopicMap().get(topicId);
            // 文件
            String fileName = ZhiHuConstant.TOPIC_ANSWER_FILE_NAME.formatted("answer", topic.getTopicName().replace("/", " "), topic.getTopicId(), "md");
            String jsonFileName = ZhiHuConstant.TOPIC_ANSWER_FILE_NAME.formatted("answerJson", topic.getTopicName().replace("/", " "), topic.getTopicId(), "json");

            if (!FileUtil.exist(fileName)) {
                initAnswerFile(topic);
            }

            map.forEach((qid, answers) -> {
                StringBuilder builder = new StringBuilder();
                builder.append("## ").append(answers.get(0).getTitle()).append("\n");
                answers.forEach(answer -> builder.append("- [%s的回答](%s),点赞数：%d，评论数：%d\n".formatted(answer.getAuthorName(), answer.getAnswerUrl(), answer.getVoteupCount(), answer.getCommentCount())));
                FileUtil.appendUtf8String(builder.toString(), fileName);
            });

            FileUtil.writeUtf8String(JSON.toJSONString(map, JSONWriter.Feature.PrettyFormat), jsonFileName);
            List<Long> qids = map.keySet().stream().toList();
            qids.forEach(qid -> table.remove(topicId, qid));
        } catch (Exception e) {
            log.error("writeAnswerFile error", e);
        } finally {
            lock.unlock();
        }

    }


    @Override
    public MatchOther processResult(ResultItems resultItems, Task task) {
        return null;
    }
}
