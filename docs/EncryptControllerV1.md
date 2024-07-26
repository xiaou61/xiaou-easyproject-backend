这个是一个api接口加密解密测试

主要是用注解来完成的。

首先我们来看加密的：

## 加密

### 类注解与声明

```java
@Slf4j
@ControllerAdvice
public class EncryptResponseAdvice implements ResponseBodyAdvice<ResponseDTO> {
```

- `@Slf4j`: 使用 Lombok 提供的注解，生成一个名为 `log` 的 Logger 对象，方便在类中使用日志记录。
- `@ControllerAdvice`: 全局控制器增强，用于处理全局异常、绑定数据或在请求/响应处理前后进行一些通用操作。这里是增强响应的功能。
- `public class EncryptResponseAdvice implements ResponseBodyAdvice<ResponseDTO>`: 实现了 `ResponseBodyAdvice` 接口，用于在响应体写入之前进行处理。泛型 `ResponseDTO` 指定了要处理的响应类型。

### 成员变量注入

```java
@Resource
private ApiEncryptService apiEncryptService;
```

- `@Resource`: 注入一个 `ApiEncryptService` 类型的 Bean，负责加密操作。

### supports 方法

```java
@Override
public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.hasMethodAnnotation(ApiEncrypt.class) || returnType.getContainingClass().isAnnotationPresent(ApiEncrypt.class);
}
```



​	判断是否支持在响应体写入之前进行处理。

- `returnType.hasMethodAnnotation(ApiEncrypt.class)`: 检查方法是否有 `ApiEncrypt` 注解。
- `returnType.getContainingClass().isAnnotationPresent(ApiEncrypt.class)`: 检查包含该方法的类是否有 `ApiEncrypt` 注解。
- 如果方法或类有 `ApiEncrypt` 注解，则返回 `true`，表示支持加密处理。

### beforeBodyWrite 方法

```java
@Override
public ResponseDTO beforeBodyWrite(ResponseDTO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    if (body.getData() == null) {
        return body;
    }

    String encrypt = apiEncryptService.encrypt(JSON.toJSONString(body.getData()));
    body.setData(encrypt);
    return body;
}
```



 在响应体写入之前进行处理。

- 参数 `ResponseDTO body`: 当前的响应体。
- 检查 `body.getData()` 是否为 `null`，如果是 `null`，直接返回 `body`，不进行加密处理。
- 如果 `body.getData()` 不是 `null`，则使用 `apiEncryptService.encrypt` 方法对其进行加密。将 `body.getData()` 转换为 JSON 字符串，然后进行加密。
- 将加密后的字符串重新设置到 `body` 的 `data` 属性中。
- 返回处理后的 `body`。

### 整体流程

1. 当控制器返回类型为 `ResponseDTO` 的响应时，`EncryptResponseAdvice` 会自动对响应进行处理。
2. `supports` 方法检查当前方法或类是否有 `ApiEncrypt` 注解，如果有则进行加密处理。
3. `beforeBodyWrite` 方法在响应体写入之前调用，检查数据是否为空，如果不为空则将数据加密，并将加密后的数据设置到响应体中。
4. 最终返回加密处理后的响应体。

之后我们来看解密

## 解密

### 类注解与声明

```java
@Slf4j
@ControllerAdvice
public class DecryptRequestAdvice extends RequestBodyAdviceAdapter {
```

- `@Slf4j`: 使用 Lombok 提供的注解，生成一个名为 `log` 的 Logger 对象，方便在类中使用日志记录。
- `@ControllerAdvice`: 全局控制器增强，用于处理全局异常、绑定数据或在请求/响应处理前后进行一些通用操作。这里是增强请求的功能。
- `public class DecryptRequestAdvice extends RequestBodyAdviceAdapter`: 实现了 `RequestBodyAdviceAdapter` 接口，用于在请求体数据读取之前和之后进行处理。

### 成员变量注入

```java
@Resource
private ApiEncryptService apiEncryptService;
```

- `@Resource`: 注入一个 `ApiEncryptService` 类型的 Bean，负责解密操作。

### 常量定义

```java
private static final String ENCODING = "UTF-8";
```

- 定义字符编码为 UTF-8，用于处理字符串和字节流的转换。

### supports 方法

```java
@Override
public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return methodParameter.hasMethodAnnotation(ApiDecrypt.class) || methodParameter.hasParameterAnnotation(ApiDecrypt.class) || methodParameter.getContainingClass().isAnnotationPresent(ApiDecrypt.class);
}
```



 判断是否支持在请求体读取之前进行处理。

- `methodParameter.hasMethodAnnotation(ApiDecrypt.class)`: 检查方法是否有 `ApiDecrypt` 注解。
- `methodParameter.hasParameterAnnotation(ApiDecrypt.class)`: 检查方法参数是否有 `ApiDecrypt` 注解。
- `methodParameter.getContainingClass().isAnnotationPresent(ApiDecrypt.class)`: 检查包含该方法的类是否有 `ApiDecrypt` 注解。
- 如果方法、参数或类有 `ApiDecrypt` 注解，则返回 `true`，表示支持解密处理。

### beforeBodyRead 方法

```java
@Override
public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    try {
        String bodyStr = IOUtils.toString(inputMessage.getBody(), ENCODING);
        ApiEncryptForm apiEncryptForm = JSONObject.parseObject(bodyStr, ApiEncryptForm.class);
        if (StringUtils.isEmpty(apiEncryptForm.getEncryptData())) {
            return inputMessage;
        }
        String decrypt = apiEncryptService.decrypt(apiEncryptForm.getEncryptData());
        return new DecryptHttpInputMessage(inputMessage.getHeaders(), IOUtils.toInputStream(decrypt, ENCODING));
    } catch (Exception e) {
        log.error("", e);
        return inputMessage;
    }
}
```



: 在请求体读取之前进行处理。

- 读取 `inputMessage` 的请求体内容并转换为字符串 `bodyStr`。
- 使用 `JSONObject.parseObject` 将字符串解析为 `ApiEncryptForm` 对象。
- 检查 `ApiEncryptForm` 对象的 `encryptData` 字段是否为空，如果为空，则直接返回原始的 `inputMessage`。
- 如果 `encryptData` 不为空，则调用 `apiEncryptService.decrypt` 方法对其进行解密，将解密后的字符串转换为输入流并创建新的 `DecryptHttpInputMessage` 对象，返回该对象。

### afterBodyRead 方法

```java
@Override
public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
}
```

- `afterBodyRead`: 在请求体读取之后进行处理。这里直接返回读取后的 `body`，没有额外处理。

### handleEmptyBody 方法

```java
@Override
public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
}
```

- `handleEmptyBody`: 处理空的请求体。这里直接返回传入的 `body`。

### DecryptHttpInputMessage 内部类

```java
static class DecryptHttpInputMessage implements HttpInputMessage {
    private final HttpHeaders headers;
    private final InputStream body;

    public DecryptHttpInputMessage(HttpHeaders headers, InputStream body) {
        this.headers = headers;
        this.body = body;
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
```



 实现，用于封装解密后的请求体。

- `private final HttpHeaders headers;`: 请求头。
- `private final InputStream body;`: 请求体。
- 构造函数初始化请求头和请求体。
- `getBody()`: 返回请求体输入流。
- `getHeaders()`: 返回请求头。

### 整体流程

1. 当控制器方法参数带有 `ApiDecrypt` 注解时，`DecryptRequestAdvice` 会自动对请求体数据进行处理。
2. `supports` 方法检查方法、参数或类是否有 `ApiDecrypt` 注解，如果有则进行解密处理。
3. `beforeBodyRead` 方法在请求体读取之前调用，读取原始请求体内容并解析为 `ApiEncryptForm` 对象。如果包含加密数据，则进行解密，并将解密后的数据封装到新的 `HttpInputMessage` 对象中。
4. 返回新的 `HttpInputMessage` 对象，使得控制器方法接收到解密后的数据。