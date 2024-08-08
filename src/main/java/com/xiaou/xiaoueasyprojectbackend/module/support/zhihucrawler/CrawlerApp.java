package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.constant.ZhiHuConstant;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model.Topic;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.process.*;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.thread.CrawlerThreadPool;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.CrawlerUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util.TopicTree;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.handler.CompositePageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.utils.HttpConstant;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.constant.ZhiHuConstant.*;


/**
 * 描述：爬虫入口
 */
@Slf4j
public class CrawlerApp {

    public static final String COOKIE = "YD00517437729195%3AWM_TID=Ggj4PQyqJXlERFQBRVOQQ94KMezwvp0I; _xsrf=TW3RBF6ux5raocFKRJY28XzqrR3r9WsX; q_c1=6234483f7a55490db41bed83d9bd84d1|1690188703000|1690188703000; YD00517437729195%3AWM_NI=UfOfII0JsGtWddlH7ZnUZ34iMoMgIYsMoMYU%2B9YpOZvUHMuJraKdSIhllbm5REupUFvpqCf9UNqfblGPm6PVMjCDT3kczxdpcjiljzawgmD6wkgOfjj4TCC8xdsKoSE1MzI%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6ee92d26881918d83f14698b48eb7d85e879a8eacc17283b5a3b2d36698b3aa86ce2af0fea7c3b92a97ba8187cc43f496bda8f75da8bca4a9b652ae8b96d4b7638fb18388cc53b193a1aedc5fe9e79791ce52b68b88aef842b2bf8585d243acb4a483f03f929bbb91d84eb6b0bfa4e5339792bf87e1688590a3d7d35e8fb68da5d739a9bdb699e77ca1b6a78de972a3a9a98ec73eb5b5a9b0e562aaec9ca4c234bcbcbdacee5987ef839bcc37e2a3; _zap=9d28d2c2-f285-47bc-baf2-f9e86706b481; d_c0=AUDSu-8q6xiPThHLub7tbUojeUgq8TbzHF8=|1720878698; z_c0=2|1:0|10:1721747593|4:z_c0|80:MS4xS05JckxnQUFBQUFtQUFBQVlBSlZUWWtXaldmODE5STJYdkJaNkl0TDAwVkRBQlNSdkl4dDh3PT0=|43648be7673444b34b3055f3ef64e7a0fa4f465eb8dd4513d55c8180574d817d; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1720878699,1721747590,1723093887; HMACCOUNT=0EDFB418B4DAF9A8; tst=r; SESSIONID=JQ7SJPmpKxuwGhzO4v43EKcXyyISrDaJOVLwYei7Hy9; JOID=WlAQBkPEFF4zIHU2QskkDSxznxpajkhpbWolVjKPbCZEah1MAEhK51ggeDtP1dKHZ_Yr_4q41PpuvsNE9paM90A=; osd=UFETA0LOFV02IX83QcwlBy1wmhtQj0tsbGAkVTeOZidHbxxGAUtP5lIhez5O39OEYvch_om91fBvvcZF_JeP8kE=; __zse_ck=001_ssuFuANCzY7NBbHf5lV1GQJ9mYrOjqCJiKkXmM/VExZH8BlKoX/z9tzWJgsxSCrxgYa1pVIqcrxC=dbId=IrixPRo6BY+FEc2JPsOgLmh088Xi=rcmRqF7jTmiMX+cUE; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1723093925; BEC=6ff32b60f55255af78892ba1e551063a";
    public static final String ANSWER_FLAG = System.getenv().get("ANSWER_FLAG");

    public final static ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();


    public static void main(String[] args) {
        // 爬取知乎热榜
        Spider.create(new HotTopProcess()).addUrl(ZhiHuConstant.HOT_TOP_URL).run();
        if (StrUtil.isEmpty(COOKIE)) {
            log.warn("请在环境变量配置cookie参数");
            return;
        }
        Boolean parseAnswer = Optional.ofNullable(ANSWER_FLAG).map(Boolean::valueOf).orElse(false);

        Spider spider = Spider.create(assemblyPage(COOKIE)).thread(Runtime.getRuntime().availableProcessors() << 5).setScheduler(new FileCacheQueueScheduler(TMP_PATH));

        CrawlerUtils.setSpider(spider);

        log.info("《《《《《《《《《《《《《《《《开始爬取topic》》》》》》》》》》》》》》");
        if (!TopicTree.checkTopic()) {
            parseTopic(spider);
        }
        //结合话题广场的话题
        spider.addUrl(TOPICS_PAGE_URL).run();
        scheduledThreadPool.shutdown();
        if (parseAnswer) {
            // 爬取话题下的问题
            parseTopicQuestion(spider, 3);
        }
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<回答爬取全部结束>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    /**
     * 爬取话题
     *
     * @param spider
     */
    public static void parseTopic(Spider spider) {
        scheduledThreadPool.scheduleWithFixedDelay(() -> {
            // 保存话题json文件
            try {
                FileUtil.writeUtf8String(JSON.toJSONString(TopicTree.getRootTopic(), JSONWriter.Feature.PrettyFormat), ZhiHuConstant.TOPIC_PATH);
                List<Topic> topics = TopicTree.TOPIC_LIST.stream().filter(topic -> topic.getFollowers() != null && topic.getFollowers() > 20000).peek(topic -> topic.setSubTopics(null)).toList();
                FileUtil.writeUtf8String(JSON.toJSONString(topics, JSONWriter.Feature.PrettyFormat), PACKAGE_PATH + File.separator + "topic" + File.separator + "heightTopics.json");
            } catch (IORuntimeException e) {
                log.error("保存数据文件失败：{}", ExceptionUtil.stacktraceToString(e));
            }
        }, 1, 1, TimeUnit.MINUTES);
        TopicTree.setRootTopic(new CopyOnWriteArrayList<>() {{
            add(new Topic().setTopicId(19776749L).setTopicName("根话题").setParentId(0L));
        }});
        Request request = new Request("https://www.zhihu.com/topic/19776749/organize");
        request.addCookie("z_c0", COOKIE);
        spider.addRequest(request).run();
        scheduledThreadPool.shutdown();
        log.info("《《《《《《《《《《《《《《topic爬取完成，开始写入topic文件》》》》》》》》》》》》》》");
    }


    /**
     * 爬取话题下高赞回答
     *
     * @param spider
     * @param threadNum 线程数 目前设置并发为2
     */
    private static void parseTopicQuestion(Spider spider, Integer threadNum) {
        spider.setExecutorService(new CrawlerThreadPool(threadNum));
        String json = FileUtil.readUtf8String(PACKAGE_PATH + File.separator + "topic" + File.separator + "heightTopics.json");
        List<Topic> list = JSON.parseArray(json, Topic.class);
        //按热度排序，优先爬取热度高的话题
        list.sort(Comparator.comparingLong(Topic::getFollowers).reversed());
        list.forEach(topic -> spider.addUrl(ZhiHuConstant.ANSWER_URL.formatted(topic.getTopicId(), 0)));
        spider.run();
    }


    /**
     * 递归添加请求
     *
     * @param topics
     * @param heightTopics
     */
    private static void forEachAddRequest(CopyOnWriteArrayList<Topic> topics, CopyOnWriteArrayList<Topic> heightTopics) {
        if (CollectionUtil.isEmpty(topics)) {
            return;
        }
        topics.stream().filter(topic -> topic.getFollowers() > 10000).forEach(topic -> {
            heightTopics.add(topic);
            if (CollectionUtil.isNotEmpty(topic.getSubTopics())) {
                forEachAddRequest(topic.getSubTopics(), topics);
            }
        });
    }


    /**
     * 基于cookie爬取话题
     *
     * @param cookie
     * @return
     */
    private static CompositePageProcessor assemblyPage(String cookie) {
        // 创建复合页面
        return new CompositePageProcessor(Site.me().setDomain("zhihu.com").setRetryTimes(3).setCycleRetryTimes(3).setCharset(StandardCharsets.UTF_8.toString()).setTimeOut(10000).setAcceptStatCode(new HashSet<Integer>() {
            {
                add(HttpConstant.StatusCode.CODE_200);
                add(HttpStatus.HTTP_NOT_FOUND);
                add(HttpStatus.HTTP_FORBIDDEN);
            }
        })).setSubPageProcessors(new TopicPageProcess(TOPIC_PAGE_PATTERN, cookie), new SubTopicProcess(TOPIC_URL), new TopAnswerProcess(ANSWER_PATTERN), new AnswerPageProcess(ANSWER_PAGE_PATTERN));
    }


}
