package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.util;

import cn.hutool.core.util.StrUtil;

import com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.constant.ZhiHuConstant;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;

import java.util.HashMap;

/**
 * 描述：
 *
 * @author zwl
 * @since 2022/5/24 16:58
 */
@UtilityClass
public class CrawlerUtils {

    @Setter
    private Spider spider;


    public static void addReqyest(String url) {
        spider.addUrl(url);
    }

    public static void close() {
        spider.close();
    }


    public static Request assemblyBody(Long topicId, Integer offset) {
        String param = StrUtil.format(ZhiHuConstant.TOPIC_PARAM, topicId, offset);
        Request request = new Request(ZhiHuConstant.TOPIC_URL);
        request.setMethod("post");
        request.setRequestBody(
                HttpRequestBody.form(
                        new HashMap<String, Object>(4) {
                            {
                                put("method", "next");
                                put("params", param);
                            }
                        },
                        "utf-8"));
        request.setCharset("utf-8");
        return request;
    }


}
