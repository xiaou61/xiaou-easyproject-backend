package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class WebUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 向客户端输出JSON字符串
     *
     * @param response HttpServletResponse
     * @param object   输出的数据
     */
    @SneakyThrows
    public static void writeJson(HttpServletResponse response, Object object) {
        writeData(response, objectMapper.writeValueAsString(object), MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 客户端返回字符串
     *
     * @param response HttpServletResponse
     * @param data     需要返回的数据
     */
    public static void writeData(HttpServletResponse response, String data, String type) {
        try {
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(data);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("writeData error", e);
        }
    }
}
