这次来介绍验证码生成的v2版本

这个是基于若依的 该类基于Spring Boot框架，结合**Google Kaptcha**库和**Redis**缓存来生成、存储和验证验证码。

之后我们来一点一点介绍

## 类和成员变量

```java
@RestController
@RequestMapping("/v2/captcha")
public class CaptchaControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(CaptchaControllerV2.class);

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;
```

这里

- 注入了两个 `Producer` 对象，用于生成不同类型的验证码。
- 注入了 `RedisCache` 对象，用于缓存验证码。

之后我们来看getCode这个的方法实现

## getcode方法实现

### 1. 创建 `AjaxResult` 对象

```java
AjaxResult ajax = AjaxResult.success();
```

- 创建一个 `AjaxResult` 对象，用于封装成功的响应结果。

### 2. 生成唯一标识 `uuid` 和构造 Redis 缓存键 `verifyKey`

```java
String uuid = IdUtils.simpleUUID();
String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
```

- 使用 `IdUtils.simpleUUID()` 生成唯一标识符 `uuid`。
- 构造验证码在 Redis 中的缓存键 `verifyKey`。

### 3. 初始化验证码相关变量

```java
String capStr = null, code = null;
BufferedImage image = null;
```

- 初始化用于存储验证码字符串、验证码结果和验证码图像的变量。

### 4. 随机选择验证码类型

```java
String captchaType = new Random().nextBoolean() ? "math" : "char";
log.info("验证码类别:{}", captchaType);
```

- 使用 `Random` 类随机选择验证码类型：数学运算验证码（"math"）或字符验证码（"char"）。
- 记录日志，打印验证码类型。

#### 5. 根据验证码类型生成验证码

```java
if ("math".equals(captchaType)) {
    String capText = captchaProducerMath.createText();
    capStr = capText.substring(0, capText.lastIndexOf("@"));
    code = capText.substring(capText.lastIndexOf("@") + 1);
    image = captchaProducerMath.createImage(capStr);
    log.info("验证码:" + capStr + "@验证码运算结果:" + code);
} else if ("char".equals(captchaType)) {
    capStr = code = captchaProducer.createText();
    image = captchaProducer.createImage(capStr);
    log.info("验证码:" + capStr);
}
```

- 如果选择的是数学运算验证码：
  - 使用 `captchaProducerMath` 生成验证码文本 `capText`。
  - 从 `capText` 中提取验证码字符串 `capStr` 和验证码结果 `code`。
  - 生成验证码图像 `image`。
  - 记录日志，打印验证码字符串和验证码结果。
- 如果选择的是字符验证码：
  - 使用 `captchaProducer` 生成验证码文本 `capStr` 和验证码结果 `code`（两者相同）。
  - 生成验证码图像 `image`。
  - 记录日志，打印验证码字符串。

#### 6. 将验证码结果存储到 Redis 缓存中

```java
redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
```

- 将验证码结果 `code` 存储到 Redis 中，键为 `verifyKey`，过期时间为 `Constants.CAPTCHA_EXPIRATION` 分钟。

#### 7. 将图像写入字节流并编码为 Base64

```java
FastByteArrayOutputStream os = new FastByteArrayOutputStream();
try {
    ImageIO.write(image, "jpg", os);
} catch (IOException e) {
    return AjaxResult.error(e.getMessage());
}
```

- 创建 `FastByteArrayOutputStream` 对象 `os`，用于存储图像字节数据。
- 使用 `ImageIO.write` 将图像 `image` 写入字节流 `os` 中，如果发生 `IOException` 异常，则返回错误信息。

#### 8. 将验证码信息放入 `AjaxResult` 并返回

```java
ajax.put("uuid", uuid);
ajax.put("img", Base64.encode(os.toByteArray()));
return ajax;
```

- 将唯一标识 `uuid` 和 Base64 编码后的图像字节数据放入 `AjaxResult` 对象 `ajax` 中。
- 返回 `ajax` 对象，包含验证码的相关信息。

这个是所使用到的验证码生成器：

```java
/**
 * 验证码文本生成器
 *

 */
public class KaptchaTextCreator extends DefaultTextCreator
{
    private static final String[] CNUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    @Override
    public String getText()
    {
        Integer result = 0;
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder suChinese = new StringBuilder();
        int randomoperands = random.nextInt(3);
        if (randomoperands == 0)
        {
            result = x * y;
            suChinese.append(CNUMBERS[x]);
            suChinese.append("*");
            suChinese.append(CNUMBERS[y]);
        }
        else if (randomoperands == 1)
        {
            if ((x != 0) && y % x == 0)
            {
                result = y / x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("/");
                suChinese.append(CNUMBERS[x]);
            }
            else
            {
                result = x + y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("+");
                suChinese.append(CNUMBERS[y]);
            }
        }
        else
        {
            if (x >= y)
            {
                result = x - y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[y]);
            }
            else
            {
                result = y - x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[x]);
            }
        }
        suChinese.append("=?@" + result);
        return suChinese.toString();
    }
}
```

以及验证码的一些配置

```java
@Configuration
public class CaptchaConfig
{
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean()
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // 验证码图片宽度 默认为200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // 验证码文本字符长度 默认为5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean(name = "captchaProducerMath")
    public DefaultKaptcha getKaptchaBeanMath()
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 边框颜色 默认为Color.BLACK
        properties.setProperty(KAPTCHA_BORDER_COLOR, "105,179,90");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // 验证码图片宽度 默认为200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
        // 验证码文本生成器
        properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, "com.xiaou.xiaoueasyprojectbackend.module.support.captcha.config.KaptchaTextCreator");
        // 验证码文本字符间距 默认为2
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
        // 验证码文本字符长度 默认为5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 验证码噪点颜色 默认为Color.BLACK
        properties.setProperty(KAPTCHA_NOISE_COLOR, "white");
        // 干扰实现类
        properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
```

可以说 这些配置可以更加灵活的实现验证码的一个生成。

可以做到高度定制