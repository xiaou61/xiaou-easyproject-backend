# SXEasy-backend

**后端通用接口代码片段汇总 | Spring Boot 3 学习与实践合集**

这是一个基于 Spring Boot 3 的后端通用接口案例项目，汇总了实际开发中常见的需求代码片段。该项目旨在帮助开发者快速搭建后端服务，解决实际业务需求中的常见问题。

------

## ✨ 项目特色

- **实用性强**：涵盖实际开发中常见的后端接口需求，适用于多种业务场景。
- **代码片段汇总**：整理并优化了常用的代码片段，方便开发者快速引用。
- **高可扩展性**：模块化设计，便于按需扩展和定制开发。

------

## 📁 项目结构

```
├── xiaou-admin       # 管理后台模块
├── xiaou-common      # 通用工具类和配置
├── xiaou-modules     # 各业务模块集合
├── sql               # 数据库脚本
├── pom.xml           # Maven 项目配置文件
└── readme.md         # 项目说明文档
```

------

## 🚀 快速开始

1. **克隆项目**

   ```cmd
   git clone https://github.com/xiaou61/SXEasy-backend.git
   ```

2. **导入项目**

   使用支持 Maven 的 IDE（如 IntelliJ IDEA）导入项目。

3. **配置数据库**

   根据 `sql` 目录下的脚本，配置并初始化数据库。

4. **运行项目**

   在 IDE 中运行主类，启动后端服务。

------

## 🧰 本项目用到的技术栈

- **Spring Boot**（https://spring.io/projects/spring-boot）
   一个用于简化 Spring 应用开发的框架，提供自动配置、快速启动器以及大量的企业级特性，提升开发效率。
- **MyBatis-Plus**（https://baomidou.com/）
   基于 MyBatis 的增强工具，简化了数据库操作，支持 Lambda 表达式、多租户、自动分页等功能。
- **Hutool**（https://hutool.cn/）
   一个 Java 工具包，提供丰富的工具类，如日期处理、加密解密、文件操作、Http 请求等，提升开发效率。
- **Lombok**（https://projectlombok.org/）
   通过注解的方式减少 Java 代码中冗余的 Getter/Setter、构造方法等样板代码，提高开发效率。
- **Springdoc OpenAPI**（https://springdoc.org/）
   用于自动生成基于 OpenAPI 规范的接口文档，支持与 Swagger UI 集成，方便接口调试与查看。
- **Spring Data Redis**（https://spring.io/projects/spring-data-redis）
   提供对 Redis 的访问抽象，支持常见的 Redis 操作，适用于缓存、分布式锁等场景。
- **P6Spy**（https://github.com/p6spy/p6spy）
   一个 SQL 性能分析工具，可以拦截和输出应用程序执行的所有 SQL 语句，便于调试和优化数据库性能。
- **Jackson**（https://github.com/FasterXML/jackson）
   Java 中常用的 JSON 解析与序列化工具，支持对象与 JSON 之间的相互转换，使用广泛。
- **MinIO**（https://min.io/）
   一个开源的对象存储服务器，兼容 Amazon S3 接口，常用于文件上传、下载与存储。
- **Spring AI**（https://github.com/spring-projects/spring-ai）
   Spring 团队推出的 AI 接入框架，支持集成 OpenAI、文档问答、嵌入生成等 AI 应用场景。
- **Maven Compiler Plugin**（https://maven.apache.org/plugins/maven-compiler-plugin/）
   Maven 官方提供的 Java 编译插件，用于控制 Java 源码的编译过程。
- **Flatten Maven Plugin**（https://www.mojohaus.org/flatten-maven-plugin/）
   用于生成精简后的 `pom.xml`，主要用于构建发布与发布时管理依赖的一致性。

------

## 📄 许可证

本项目采用 MIT 许可证

