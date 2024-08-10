package com.xiaou.xiaoueasyprojectbackend.module.support.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;
import java.util.function.Consumer;

/**
 *  代码生成器
 *  AutoGenerator 是 MyBatis-Plus 的代码生成器，通过 AutoGenerator 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码，极大的提升了开发效率。
 *  适用版本：mybatis-plus-generator 3.5.1 以上版本
 *
 * @author wangchenghai
 * @date 2023/01/09 9:45:15
 */
public class Generator {
    /**路径*/
    private static final String PROJECT_PATH  = "D:\\onenodes\\githubprojectstart\\xiaou-easyproject-backend\\gen";
    /** 自定义文件路径*/
    private static final String OTHER_PATH = PROJECT_PATH + "/src/main/java/com/xiaou/note";
    /**数据库地址*/
    private static final String URL = "jdbc:mysql://localhost:3306/xiaou_easy_project?&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    /** 数据库账号*/
    private static final String USERNAME = "root";
    /** 数据库密码*/
    private static final String PASSWORD = "1234";
    /** 父包名 */
    private static final String PARENT_PACKAGE = "com.xiaou";
    /**父包模块名*/
    private static final String MODULE_NAME = "gen";
    /** 公共类 */
    private static final String COMMON_ENTITY = "com.xiaou.common.daomain.BaseEntity";
    /** 公共字段*/
    private static final String[] COMMON_FIELD = {"updated_by","updated_time","created_by","created_time"};

    /**
     * 读取控制台内容
     *
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    public static void main(String[] args) {
        FastAutoGenerator
                .create(getDataSourceConfig())
                .globalConfig(getGlobalConfig())
                .packageConfig(getPackageConfig())
                //模板引擎修改
                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(getTemplateConfig())
                .injectionConfig(initInjectionConfig())
                .strategyConfig(getStrategyConfig())
                .execute();
    }
    /**
     * 数据库配置(DataSourceConfig)
     * @return
     */
    private static DataSourceConfig.Builder getDataSourceConfig() {
        return new DataSourceConfig.Builder(URL,USERNAME,PASSWORD);
    }

    /**
     * 全局配置(GlobalConfig)
     * @return
     */
    private static Consumer<GlobalConfig.Builder> getGlobalConfig() {

        return builder -> builder
                //禁止打开输出目录
                .disableOpenDir()
                // 开启 Springdoc 模式
                .enableSpringdoc()
                // 设置作者
                .author(scanner("请输入作者名称"))
                //指定输出目录
                .outputDir(PROJECT_PATH + "/src/main/java")
                //时间策略 java8 新的时间类型支持线程安全的。
                .dateType(DateType.TIME_PACK)
                //注释日期	默认值: yyyy-MM-dd
                .commentDate("yyyy-MM-dd")
                .build();
    }

    /**
     * 包配置(PackageConfig)
     */
    private static Consumer<PackageConfig.Builder> getPackageConfig() {
        return builder -> builder
                //设置父包模块名
                .parent(PARENT_PACKAGE)
                //设置父包名
                .moduleName(MODULE_NAME)
                // 设置 Entity 包名
                .entity("pojo.entity")
                // 设置 Service 包名
                .service("service")
                // 设置 Service Impl 包名
                .serviceImpl("service.impl")
                //Mapper 包名
                .mapper("dao")
                //Mapper XML 包名
                .xml("mapper.xml")
                // 设置 Controller 包名
                .controller("controller")
                // 设置mapperXml生成路径
                .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + "/src/main/resources/mapper/"))
                .build();
    }

    /**
     * 模板配置(TemplateConfig)
     * @return
     */
    private static Consumer<TemplateConfig.Builder> getTemplateConfig() {
        return builder -> builder
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
    }

    /**
     * 自定义生成模板
     * @return
     */
    private static Consumer<InjectionConfig.Builder>  initInjectionConfig() {
        /**自定义生成模板参数**/
        Map<String, Object> paramMap = new HashMap<>();
        Other other=new Other();
        other.setDto(PARENT_PACKAGE+"."+MODULE_NAME+".pojo.dto");
        other.setVo(PARENT_PACKAGE+"."+MODULE_NAME+".pojo.vo");
        other.setQuery(PARENT_PACKAGE+"."+MODULE_NAME+".pojo.query");
        paramMap.put("other",other);
        List<CustomFile> customFiles=new ArrayList<>();
        /**DTO实体**/
        customFiles.add(new CustomFile.Builder()
                //模板路径
                .templatePath("/templates/dto.java.ftl")
                //文件名称
                .fileName("Dto.java")
                //文件路径
                .filePath(OTHER_PATH)
                //自定义文件包名
                .packageName("pojo/dto")
                //是否覆盖已有文件（默认 false）
                .enableFileOverride().build());
        /**Vo实体**/
        customFiles.add(new CustomFile.Builder()
                //模板路径
                .templatePath("/templates/vo.java.ftl")
                //文件名称
                .fileName("Vo.java")
                //文件路径
                .filePath(OTHER_PATH)
                //自定义文件包名
                .packageName("pojo/vo")
                //是否覆盖已有文件（默认 false）
                .enableFileOverride().build());
        /**Query实体**/
        customFiles.add(new CustomFile.Builder()
                //模板路径
                .templatePath("/templates/query.java.ftl")
                //文件名称
                .fileName("Query.java")
                //文件路径
                .filePath(OTHER_PATH)
                //自定义文件包名
                .packageName("pojo/query")
                //是否覆盖已有文件（默认 false）
                .enableFileOverride().build());

        return consumer->consumer
                // 自定义配置 Map 对象
                .customMap(paramMap)
                //自定义模板文件列表
                .customFile(customFiles)
                .beforeOutputFile((tableInfo, objectMap) -> {
                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                });
    }

    /**
     * 处理 all 情况
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    /**
     * 策略配置(StrategyConfig)
     * @return
     */
    private static Consumer<StrategyConfig.Builder> getStrategyConfig(){
        return builder -> {

            /**
             * 策略配置
             */
                    //设置过滤表前缀
            builder .addTablePrefix(scanner("请输需要过滤表前缀"))
                    //设置需要生成的表
                    .addInclude(getTables(scanner("请输入表名，多个英文逗号分隔？所有输入 all")))
                    .build();

            /**
             * Entity 策略配置
             */
            builder.entityBuilder()
                    // 覆盖已生成文件
                    .enableFileOverride()
                    //设置父类
                    .superClass(COMMON_ENTITY)
                    // 开启 lombok 模型
                    .enableLombok()
                    //数据库表映射到实体的命名策略
                    .naming(NamingStrategy.underline_to_camel)
                    //添加父类公共字段
                    .addSuperEntityColumns(COMMON_FIELD)
                    // 全局主键类型
                    .idType(IdType.AUTO)
                    //格式化文件名称
//                    .formatFileName("%sEntity")
                    .build();

            /**
             * Controller 策略配置
             */
            builder.controllerBuilder()
                    // 覆盖已生成文件
                    .enableFileOverride()
                    // 开启生成@RestController 控制器
                    .enableRestStyle()
                    //开启驼峰转连字符  @RequestMapping("/managerUserActionHistory") -> @RequestMapping("/manager-user-action-history")
                    .enableHyphenStyle()
                    // 转换文件名称
                    .formatFileName("%sController")
                    .build();

            /**
             * Service 策略配置
             */
            builder.serviceBuilder()
                    // 覆盖已生成文件
                    .enableFileOverride()
                    //格式化 service 接口文件名称
                    .formatServiceFileName("%sService")
                    //格式化 service 实现类文件名称
                    .formatServiceImplFileName("%sServiceImpl")
                    .build();

            /**
             * Mapper 策略配置
             */
            builder.mapperBuilder()
                    // 覆盖已生成文件
                    .enableFileOverride()
                    // 启用 BaseResultMap 生成
                    .enableBaseResultMap()
                    // 启用 BaseColumnList
                    .enableBaseColumnList()
                    .build();
        };
    }
}
