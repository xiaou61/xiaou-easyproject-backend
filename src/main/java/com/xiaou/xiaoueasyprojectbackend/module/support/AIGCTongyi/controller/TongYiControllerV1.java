package com.xiaou.xiaoueasyprojectbackend.module.support.AIGCTongyi.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.fastjson2.JSON;
import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaou61
 * @Date 2024/7/24 下午22:44
 * @Description 通义千问对接
 */
@RestController
@RequestMapping("/v1/tongyi")
@Tag(name = "通义千问接口V1", description = "通义千问接口")
public class TongYiControllerV1 {

    private final String apikey="sk-ad40dbef9a2d4f318085d4d7fb519346";
    @Resource
    private Generation generation;




    /**
     * @Author xiaou61
     * @Date 2024/7/24 下午10:55
     * @Description 正常输出
     * @Since version 1.0
     */
    @PostMapping(value = "/send")
    public String send(@RequestBody String content) throws NoApiKeyException, InputRequiredException {
        //用户与模型的对话历史。list中的每个元素形式为{“role”:角色, “content”: 内容}。
        Message userMessage = Message.builder()
                .role(Role.USER.getValue())
                .content(content)
                .build();

        GenerationParam param = GenerationParam.builder()
                //指定用于对话的通义千问模型名
                .model("qwen-turbo")
                .messages(Arrays.asList(userMessage))
                //
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                //生成过程中核采样方法概率阈值，例如，取值为0.8时，仅保留概率加起来大于等于0.8的最可能token的最小集合作为候选集。
                // 取值范围为（0,1.0)，取值越大，生成的随机性越高；取值越低，生成的确定性越高。
                .topP(0.8)
                //阿里云控制台DASHSCOPE获取的api-key
                .apiKey(apikey)
                //启用互联网搜索，模型会将搜索结果作为文本生成过程中的参考信息，但模型会基于其内部逻辑“自行判断”是否使用互联网搜索结果。
                .enableSearch(true)
                .build();
        GenerationResult generationResult =generation.call(param);;
        return generationResult.getOutput().getChoices().get(0).getMessage().getContent();
    }



    /**
     * @Author xiaou61
     * @Date 2024/7/24 下午10:53
     * @Description 流式输出
     * @Since version 1.0
     */
    @PostMapping(value = "/aiTalk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> aiTalk(@RequestBody String question, HttpServletResponse response)
            throws NoApiKeyException, InputRequiredException {
        Message message = Message.builder()
                .role(Role.USER.getValue())
                .content(question).build();

        QwenParam qwenParam = QwenParam.builder()
                .model(Generation.Models.QWEN_PLUS)
                .messages(Collections.singletonList(message))
                .topP(0.8)
                .resultFormat(QwenParam.ResultFormat.MESSAGE)
                .enableSearch(true)
                .apiKey(apikey)
                .incrementalOutput(true)
                .build();
        Flowable<GenerationResult> result = generation.streamCall(qwenParam);


        return Flux.from(result)
                .map(m -> {
                    // GenerationResult对象中输出流(GenerationOutput)的choices是一个列表，存放着生成的数据。
                    String content = m.getOutput().getChoices().get(0).getMessage().getContent();
                    return ServerSentEvent.<String>builder().data(content).build();
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnError(e -> {
                    Map<String, Object> map = new HashMap<>(){{
                        put("code", "400");
                        put("message", "出现了异常，请稍后重试");
                    }};
                    try {
                        response.getOutputStream().print(JSON.toJSONString(map));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }

}
