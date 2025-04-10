package org.dromara.weixinqrcode.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.weixinqrcode.util.ApiResultUtil;
import org.dromara.weixinqrcode.util.JwtUtil;

/**
 * JWT过滤器，拦截 /user/* 请求
 */
@Slf4j
@WebFilter(filterName = "JwtFilter", urlPatterns = {"/user/*"})
public class JwtFilter implements Filter {
    private static List<String> EXCLUDE_PATH_LIST = new ArrayList<>();

    static {
        EXCLUDE_PATH_LIST.add("/user/qrcode");
        EXCLUDE_PATH_LIST.add("/user/login/qrcode");
        EXCLUDE_PATH_LIST.add("/weixin/check");
    }

    private JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse response = (HttpServletResponse)res;
        log.info("session id:{}", request.getRequestedSessionId());

        response.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        for (String excludePath : EXCLUDE_PATH_LIST) {
            if (excludePath.startsWith(path)) {
                chain.doFilter(request, response);
                return;
            }
        }
        // 除OPTIONS外，其他请求应由JWT进行检查
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        //获取请求头里的token
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !StringUtils.startsWith(authorization, "Bearer ")) {
            response.getWriter().write(ApiResultUtil.error("authentication failed"));
            return;
        }
        authorization = StringUtils.replaceOnce(authorization, "Bearer ", StringUtils.EMPTY);
        Map<String, Claim> userData = jwtUtil.verifyToken(authorization);
        if (userData == null) {
            response.getWriter().write(ApiResultUtil.error("authentication failed"));
            return;
        }
        String openId = userData.get(JwtUtil.OPEN_ID).asString();
        //拦截器 拿到用户信息，放到request中
        request.setAttribute(JwtUtil.OPEN_ID, openId);
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
