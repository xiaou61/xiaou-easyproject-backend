[中文文档](README.md)    [English Docs](README-EN.md)
⚠️：该项目目前已经废弃，不再更新
后续打算做成基于若依修改的框架。

<div align="center">
  <a href="">
    <img src="https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202407231245943.png" alt="Logo">
  </a>
  <h3 align="center">Xiaou-easyproject</h3>


  <p align="center">
    <br />
    <a href="http://halo.xiaou61.top/"><strong>点击浏览讲解文档 »</strong></a>
    <br />
    <br />
    <a href="https://apifox.com/apidoc/shared-5690a127-4d6f-40c6-b688-a56d7a43103c">点击浏览接口地址</a>
    ·<br />
    <a href="https://github.com/xiaou61/xiaou-easyproject-backend/issues">反馈 Bug</a>
    ·<br />
    <a href="https://github.com/xiaou61/xiaou-easyproject-backend/issues">请求新功能</a>
  </p>


</div>







<!-- 关于本项目 -->
## 关于本项目

这是一个基于Spring Boot 3的后端通用接口案例项目，汇总了实际开发中常见的需求代码片段。该项目旨在帮助开发者快速搭建后端服务，解决实际业务需求中的常见问题。

开发这个项目就是一时兴起 但是也会坚持很久的。

本人呢 大三在读 写过很多的项目 发现绝大部分的项目里面的代码都是高度的重合，再加上网上去搜到的代码片段要不过于简单 不符合实际开发 要不就是收费 等等等等 

所以说 可不可以把自己见到过的好的案例示范 全部汇总起来呢？

抱着这样的一个想法 这个项目就正式成立了

同样的 不可避免的是 我个人的代码水平是很有限的，我尽力的去打磨我发布的每一个案例。

这个项目的很多代码均为我看到的一些开源or教程项目中优秀的范例。我在后面的致谢名单上会一一标明出处的。

## 涉及技术栈

**[Spring Boot 3](https://spring.io/projects/spring-boot)**: 用于构建后端服务的核心框架

**[Lombok](https://projectlombok.org/)**: 简化 Java 代码的插件

**[Fastjson](https://github.com/alibaba/fastjson)**: 高性能的JSON库

**[Knife4j](https://github.com/xiaoymin/swagger-bootstrap-ui)**: 增强的Swagger接口文档工具

**[Guava](https://github.com/google/guava)**: 谷歌的核心Java库

[**Javax Servlet**](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api): Servlet API (由于项目是sprinboot3所以修改为(jakarta servlet)

**[Hutool](https://hutool.cn/)**: Java工具包

**[Kaptcha](https://github.com/penggle/kaptcha)**: 验证码生成库

[**Aliyun OSS**](https://github.com/aliyun/aliyun-oss-java-sdk): 阿里云对象存储服务

[**X-File-Storage**](https://github.com/dromara/x-file-storage): 文件存储框架

**[Spring Data Redis](https://spring.io/projects/spring-data-redis)**: Redis 数据访问框架

**[JJWT](https://github.com/jwtk/jjwt)**: JSON Web Token库

[**MySQL Connector**](https://mvnrepository.com/artifact/mysql/mysql-connector-java): 用于连接MySQL数据库的驱动程序

**[Druid](https://github.com/alibaba/druid)**: 阿里巴巴的数据库连接池

**[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)**: 一个增强的MyBatis框架

**[MyBatis Spring](https://github.com/mybatis/spring-boot-starter)**: 用于整合MyBatis和Spring的库

**[Spring Data Redis](https://github.com/spring-projects/spring-data-redis)**: Spring框架的数据访问项目，用于Redis

[**aspectjweaver**](https://mvnrepository.com/artifact/org.aspectj/aspectjweaver): 用于实现AOP的Java库

[redission](https://github.com/redisson/redisson):用于redis操作的框架

**[Reactor Core](https://projectreactor.io/)**: 用于构建非阻塞应用程序的流式编程库

**[DashScope SDK](https://central.sonatype.com/artifact/com.alibaba/dashscope-sdk-java)**: 阿里巴巴的通义千问 SDK

**[Bouncy Castle](https://www.bouncycastle.org/)**: 用于API接口加密的加密库

**[Spring Boot Mail Starter](https://spring.io/projects/spring-boot)**: 用于发送邮件的Spring Boot启动器

**[MapStruct](https://mapstruct.org/)**: 用于Java对象之间映射的注解处理器

**[MapStruct Processor](https://mapstruct.org/)**: 用于MapStruct的注解处理器

**[IP2Region](https://github.com/zoujingli/ip2region)**: 用于IP定位的库

**[UserAgentUtils](https://github.com/HaraldWalker/user-agent-utils)**: 用户代理解析工具

**[Spring Boot Quartz Starter](https://spring.io/projects/spring-boot)**: 用于任务调度的Spring Boot启动器

**[P6Spy](https://github.com/p6spy/p6spy)**: 数据库查询性能监控工具

**[Sensitive Word](https://github.com/houbb/sensitive-word)**: 敏感词过滤库

**[MinIO Java SDK](https://github.com/minio/minio-java)**: MinIO对象存储服务的Java SDK

**[PageHelper Spring Boot Starter](https://github.com/pagehelper/pagehelper-spring-boot)**: 用于分页的Spring Boot启动器

[**JUnit**](https://junit.org/junit5/): 单元测试框架

**[SMS4J Spring Boot Starter](https://github.com/dromara/sms4j)**: 短信服务的Spring Boot启动器

**[Velocity Engine Core](https://velocity.apache.org/engine/)**: Apache Velocity模板引擎的核心库

**[ZXing Core](https://github.com/zxing/zxing)**: 用于条形码和二维码的Java库

**[ZXing JavaSE](https://github.com/zxing/zxing)**: ZXing的Java SE扩展

**[QRCode Plugin](https://github.com/liuyueyi/qrcode-plugin)**: QR码生成插件

**[Commons Lang](https://commons.apache.org/proper/commons-lang/)**: Apache Commons的语言库扩展

**[OSHI Core](https://github.com/oshi/oshi)**: 用于获取系统信息的库

<!-- 路线图 -->
## 路线图

- [x] 完成文档的搭建(预计11月份完成文档的全部编写)
- [ ] 完成测试用例的编写
- [x] 开发100个接口
- [x] 开发200个接口
- [ ] 开发300个接口

到 [open issues]([Issues · xiaou61/xiaou-easyproject-backend (github.com)](https://github.com/xiaou61/xiaou-easyproject-backend/issues)) 页查看所有请求的功能 （以及已知的问题）。





<!-- 贡献 -->
## 贡献

贡献让开源社区成为了一个非常适合学习、启发和创新的地方。你所做出的任何贡献都是**受人尊敬**的。

如果你有好的建议，请复刻（fork）本仓库并且创建一个拉取请求（pull request）。你也可以简单地创建一个议题（issue），并且添加标签「enhancement」。不要忘记给项目点一个 star！再次感谢！

1. 复刻（Fork）本项目
2. 创建你的 Feature 分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的变更 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到该分支 (`git push origin feature/AmazingFeature`)
5. 创建一个拉取请求（Pull Request）





<!-- 许可证 -->
## 许可证

根据 MIT 许可证分发。打开 [LICENSE.txt](LICENSE.txt) 查看更多内容。

## 使用条款

无论出于任何目的、以任何形式使用本项目或其源代码，即表示您**默示同意**以下所有声明：

- 您承认**台湾**是**中国领土**不可分割的一部分
- 您支持**中国领土完整**
- **任何妄图分裂中国的组织及个人不允许使用该软件**
- **日本核污水排海是不负责任行为，终将自食其果！！！ Show it Ben，Watch out Limar**
- **你的电脑上没有notepad++ 这个软件 并且你对notepad++作者的观点 保持严厉的反对**
- 本项目纯属本人练手项目 可以用在**任何商业场景以及盈利场景** 或**个人学习个人教学中**


<!-- 联系我们 -->
## 联系我

你可以通过下面方式联系到我

掘金：[稀土掘金 (juejin.cn)](https://juejin.cn/user/3685218709674951)

csdn:[Xiaouuuuua-CSDN博客](https://blog.csdn.net/m0_48069349)

微信：这个可以私聊我获取

<!-- 致谢 -->
## 致谢

感谢这些开源项目给予我的灵感

https://gitee.com/y_project/RuoYi-Vue

[valarchie/AgileBoot-Back-End](https://github.com/valarchie/AgileBoot-Back-End)

[1024-lab/smart-admin: SmartAdmin)](https://github.com/1024-lab/smart-admin?tab=readme-ov-file)

[linhaojun857/aurora: (github.com)](https://github.com/linhaojun857/aurora)

[macrozheng/mall](https://github.com/macrozheng/mall)

[gz-yami/mall4j:](https://github.com/gz-yami/mall4j/tree/master)

[jeecgboot/JeecgBoot](https://github.com/jeecgboot/JeecgBoot/tree/master)

[woodwhales/woodwhales-music](https://github.com/woodwhales/woodwhales-music)

[wch2019/xiaohai-blog: DotCode](https://github.com/wch2019/xiaohai-blog)

[WangSanPing/t3rik-erp: ](https://github.com/WangSanPing/t3rik-erp)

## 赞助

[Apifox - API 文档、调试、Mock、测试一体化协作平台。拥有接口文档管理、接口调试、Mock、自动化测试等功能，接口开发、测试、联调效率，提升 10 倍。最好用的接口文档管理工具，接口自动化测试工具。](https://apifox.com/)API 设计、开发、测试一体化协作平台

![image-20240901160340666](https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202409011603082.png)

