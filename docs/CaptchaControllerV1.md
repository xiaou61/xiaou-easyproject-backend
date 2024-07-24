这一篇来讲一下CaptchaControllerV1的实现

首先我在这里定义了俩个方法 一个是通过hutool实现的 一个是通过Kaptcha来实现的

首先我们来看hutool实现的

## hutool

先看一个测试结果

![image-20240724111931179](https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202407241119312.png)

之后这个是源码：

```java
@GetMapping("/hutool")
public void HutoolVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //定义图形验证码的长、宽、验证码字符数、干扰线宽度
    ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
    //图形验证码写出，可以写出到文件，也可以写出到流
    captcha.write(response.getOutputStream());
    //获取验证码中的文字内容
    String verifyCode = captcha.getCode();
    log.info("验证码为:{}", verifyCode);
    request.getSession().setAttribute("verifyCode", verifyCode);
}
```

其实用hutool的话很简单 因为他把生成验证码的步骤简化成为了一个工具类createShearCaptcha。

通过看源码可以看到他的参数

![image-20240724112104525](https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202407241121564.png)

我们这里采用的方法是，把正确的验证码写入到session里面的一个verifyCode的字段。之后我们在登录的时候去getattribute去验证这个字段就可以了。

## Kaptcha

这个是测试结果如图

![image-20240724112310832](https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202407241123916.png)

这个是他的代码

```java
@GetMapping("/kaptcha")
public void KaptchaVerify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    byte[] captchaOutputStream = null;
    ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
    try {
        //生产验证码字符串并保存到session中
        String verifyCode = captchaProducer.createText();
        log.info("验证码为:{}", verifyCode);
        httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
        BufferedImage challenge = captchaProducer.createImage(verifyCode);
        ImageIO.write(challenge, "jpg", imgOutputStream);
    } catch (IllegalArgumentException e) {
        httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
    }
    captchaOutputStream = imgOutputStream.toByteArray();
    httpServletResponse.setHeader("Cache-Control", "no-store");
    httpServletResponse.setHeader("Pragma", "no-cache");
    httpServletResponse.setDateHeader("Expires", 0);
    httpServletResponse.setContentType("image/jpeg");
    ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
    responseOutputStream.write(captchaOutputStream);
    responseOutputStream.flush();
    responseOutputStream.close();
}
```

相比于hutool 这个代码需要我们自己去创建很多东西

下面我讲详细解释代码：

1. **初始化变量**：

   ```java
   byte[] captchaOutputStream = null;
   ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
   ```

   - `captchaOutputStream`：用于存储验证码图像的字节数组。
   - `imgOutputStream`：创建一个字节数组输出流`ByteArrayOutputStream`，用于将生成的验证码图像写入内存。

2. **生成验证码并保存到会话中**：

   ```java
   try {
       String verifyCode = captchaProducer.createText();
       log.info("验证码为:{}", verifyCode);
       httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
       BufferedImage challenge = captchaProducer.createImage(verifyCode);
       ImageIO.write(challenge, "jpg", imgOutputStream);
   } catch (IllegalArgumentException e) {
       httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
       return;
   }
   ```

   - `String verifyCode = captchaProducer.createText();`：生成验证码字符串。
   - `log.info("验证码为:{}", verifyCode);`：记录验证码字符串。
   - `httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);`：将验证码字符串保存到当前会话的属性中，键为`"verifyCode"`。
   - `BufferedImage challenge = captchaProducer.createImage(verifyCode);`：根据验证码字符串生成验证码图像。
   - `ImageIO.write(challenge, "jpg", imgOutputStream);`：将验证码图像写入字节数组输出流`imgOutputStream`。
   - 如果捕获到`IllegalArgumentException`异常，返回404错误响应，并终止方法执行。

3. **设置响应头**：

   ```java
   captchaOutputStream = imgOutputStream.toByteArray();
   httpServletResponse.setHeader("Cache-Control", "no-store");
   httpServletResponse.setHeader("Pragma", "no-cache");
   httpServletResponse.setDateHeader("Expires", 0);
   httpServletResponse.setContentType("image/jpeg");
   ```

   - `captchaOutputStream = imgOutputStream.toByteArray();`：将字节数组输出流转换为字节数组。
   - 设置HTTP响应头，禁止缓存验证码图像：
     - `"Cache-Control"`: `"no-store"`：指示浏览器不要存储响应内容。
     - `"Pragma"`: `"no-cache"`：兼容HTTP/1.0，指示不要缓存任何内容。
     - `"Expires"`: `0`：设置过期时间为0，立即过期。
     - `"Content-Type"`: `"image/jpeg"`：指示响应的内容类型为JPEG图像。

4. **写入响应输出流**：

   ```java
   ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
   responseOutputStream.write(captchaOutputStream);
   responseOutputStream.flush();
   responseOutputStream.close();
   ```

   - 获取响应的输出流`ServletOutputStream`。
   - 将验证码图像字节数组写入输出流。
   - 刷新输出流，确保所有数据已写入。
   - 关闭输出流。

这样，客户端在访问`/kaptcha`路径时，会收到一个不缓存的JPEG格式的验证码图像，且该验证码字符串会被保存到服务器的会话中。