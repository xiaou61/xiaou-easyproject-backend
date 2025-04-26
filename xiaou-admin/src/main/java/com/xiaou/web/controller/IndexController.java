package com.xiaou.web.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.xiaou.utils.R;
import com.xiaou.utils.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Slf4j
@RestController
@Tag(name = "首页")
public class IndexController {


    @Resource
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，请通过前端地址访问。", SpringUtil.getApplicationName());
    }


    @Autowired
    public void ApiListController(ApplicationContext context) {
        this.handlerMapping = context.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
    }

    @GetMapping("/api/list")
    @Operation(summary = "获取所有API列表")
    public R<Map<String, Object>> listAllApi() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        List<Map<String, String>> apiList = new ArrayList<>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            Set<String> paths = new HashSet<>();

            // Spring Boot 3 推荐方式获取路径
            if (info.getPathPatternsCondition() != null) {
                info.getPathPatternsCondition().getPatterns()
                        .forEach(p -> paths.add(p.getPatternString()));
            }

            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();

            for (String path : paths) {
                if (methods.isEmpty()) {
                    apiList.add(Map.of("method", "ANY", "path", path));
                } else {
                    for (RequestMethod method : methods) {
                        apiList.add(Map.of("method", method.name(), "path", path));
                    }
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", apiList.size());
        result.put("apis", apiList);

        return R.ok(result);
    }


}
