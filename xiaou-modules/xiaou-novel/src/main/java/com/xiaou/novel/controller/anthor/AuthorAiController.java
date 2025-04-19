package com.xiaou.novel.controller.anthor;

import com.xiaou.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "AiController", description = "作家后台-AI模块")
@RequestMapping("/novel/author/ai")
public class AuthorAiController {

    @Resource
    private ChatClient chatClient;

    /**
     * AI扩写
     */
    @Operation(summary = "AI扩写接口")
    @PostMapping("/expand")
    public R<String> expandText(@RequestParam("text") String text,
                                @RequestParam("ratio") Double ratio,
                                @RequestParam(value = "style", defaultValue = "正常") String style) {
        String prompt = String.format(
                "请将以下文本扩写，使其内容更丰富，扩写后的长度大约是原文的 %.0f%%。请保持语义连贯，语言风格为%s：\n\n%s",
                ratio, style, text);
        return R.ok(chatClient.prompt().user(prompt).call().content());
    }

    /**
     * AI缩写
     */
    @Operation(summary = "AI缩写接口")
    @PostMapping("/condense")
    public R<String> condenseText(@RequestParam("text") String text,
                                  @RequestParam("ratio") Integer ratio) {
        String prompt = String.format(
                "请将以下文本进行浓缩，保留关键信息，使内容简洁明了。目标为压缩至原文约 %d%% 的长度：\n\n%s",
                ratio, text);
        return R.ok(chatClient.prompt().user(prompt).call().content());
    }

    /**
     * AI续写
     */
    @Operation(summary = "AI续写接口")
    @PostMapping("/continue")
    public R<String> continueText(@RequestParam("text") String text,
                                  @RequestParam("length") Integer length,
                                  @RequestParam(value = "style", defaultValue = "正常") String style) {
        String prompt = String.format(
                "请续写以下内容，续写部分大约为 %d 字。请保持原文的语气和风格为%s：\n\n%s",
                length, style, text);
        return R.ok(chatClient.prompt().user(prompt).call().content());
    }

    /**
     * AI润色
     */
    @Operation(summary = "AI润色接口")
    @PostMapping("/polish")
    public R<String> polishText(@RequestParam("text") String text,
                                @RequestParam(value = "style", defaultValue = "正常") String style) {
        String prompt = String.format(
                "请对以下文本进行润色优化，使语言更加流畅自然，风格为%s，同时保持原意不变：\n\n%s",
                style, text);
        return R.ok(chatClient.prompt().user(prompt).call().content());
    }
}
