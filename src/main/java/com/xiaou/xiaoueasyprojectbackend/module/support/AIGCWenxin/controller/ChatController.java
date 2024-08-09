package com.xiaou.xiaoueasyprojectbackend.module.support.AIGCWenxin.controller;

import com.gearwenxin.entity.response.ChatResponse;
import com.gearwenxin.model.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * @author xiaou61
 * @Date 2024/8/9 下午16:06
 * @Description 文心一言对接
 * 采用开源项目https://github.com/egmsia01/wenxin-starter
 * 需要配置 这里不在配置 可以去开源项目里面查找用法
 */
@RestController
public class ChatController {

    // 要调用的模型的客户端（示例为文心）
    @Resource
    @Qualifier("Ernie")
    private ChatModel chatClient;
  
    /**
     * chatClient.chatStream(msg) 单轮流式对话
     * chatClient.chatStream(new ChatErnieRequest()) 单轮流式对话, 参数可调
     * chatClient.chatsStream(msg, msgId) 连续对话
     * chatClient.chatsStream(new ChatErnieRequest(), msgId) 连续对话, 参数可调
     */
  
    /**
     * 以下两种方式均可
     */
    // 连续对话，流式
    @GetMapping(value = "/stream/chats", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatSingleStream(@RequestParam String msg, @RequestParam String uid) {
        // 单次对话 chatClient.chatStream(msg)
        Flux<ChatResponse> responseFlux = chatClient.chatsStream(msg, uid);
        return responseFlux.map(ChatResponse::getResult);
    }
  
    // 连续对话，流式
    @GetMapping(value = "/stream/chats1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chats(@RequestParam String msg, @RequestParam String uid) {
        SseEmitter emitter = new SseEmitter();
        // 支持参数设置 ChatErnieRequest（Ernie系列模型）、ChatBaseRequest（其他模型）
        // 单次对话 chatClient.chatsStream(msg)
        chatClient.chatsStream(msg, uid).subscribe(response -> {
            try {
                emitter.send(SseEmitter.event().data(response.getResult()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return emitter;
    }

}