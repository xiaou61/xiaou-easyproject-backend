这个是对接通义千问Api的一个示例

### 方法实现

#### 正常输出

```java
@PostMapping(value = "/send")
public String send(@RequestBody String content) throws NoApiKeyException, InputRequiredException {
    // 用户与模型的对话历史
    Message userMessage = Message.builder()
            .role(Role.USER.getValue())
            .content(content)
            .build();

    GenerationParam param = GenerationParam.builder()
            .model("qwen-turbo")
            .messages(Arrays.asList(userMessage))
            .resultFormat(GenerationParam.ResultFormat.MESSAGE)
            .topP(0.8)
            .apiKey(apikey)
            .enableSearch(true)
            .build();

    GenerationResult generationResult = generation.call(param);
    return generationResult.getOutput().getChoices().get(0).getMessage().getContent();
}
```

- **`@PostMapping(value = "/send")`**：定义了一个 POST 请求的端点，路径为 `/v1/tongyi/send`。
- **`@RequestBody String content`**：接收请求体中的内容，类型为 `String`。
- **`Message`**：构建一个消息对象，角色为用户，内容为请求体中的内容。
- **`GenerationParam`**：设置生成请求的参数，包括模型名、消息列表、结果格式、API 密钥等。
- **`generation.call(param)`**：调用通义千问生成接口，返回生成结果。
- **返回值**：从生成结果中提取生成的内容并返回。

#### 流式输出

```java
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
                String content = m.getOutput().getChoices().get(0).getMessage().getContent();
                return ServerSentEvent.<String>builder().data(content).build();
            })
            .publishOn(Schedulers.boundedElastic())
            .doOnError(e -> {
                Map<String, Object> map = new HashMap<>() {{
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
```

- **`@PostMapping(value = "/aiTalk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)`**：定义了一个 POST 请求的端点，路径为 `/v1/tongyi/aiTalk`，并指定返回类型为 `text/event-stream`，用于流式输出。
- **`@RequestBody String question`**：接收请求体中的问题内容，类型为 `String`。
- **`Message`**：构建一个消息对象，角色为用户，内容为请求体中的问题。
- **`QwenParam`**：设置流式生成请求的参数，包括模型名、消息列表、结果格式、API 密钥等，启用增量输出。
- **`generation.streamCall(qwenParam)`**：调用通义千问流式生成接口，返回一个 `Flowable` 对象，用于处理流式输出。
- **`Flux.from(result)`**：将 `Flowable` 转换为 `Flux`，用于响应流式数据。
- **`ServerSentEvent`**：构建 Server-Sent Events 响应，每个事件包含生成的内容。
- **`publishOn(Schedulers.boundedElastic())`**：指定流式处理的调度器。
- **`doOnError`**：处理错误情况，如果出现异常，将错误信息写入响应输出流。

### 总结

- **`send` 方法**：处理正常的生成请求，并返回生成的内容。
- **`aiTalk` 方法**：处理流式生成请求，并通过 Server-Sent Events 实时推送生成内容。
- **错误处理**：在流式输出中处理错误情况，将错误信息写入响应输出流。