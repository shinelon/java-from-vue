package com.javaglm.task.security;

import com.javaglm.task.common.BizException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 拦截器（第 29 章）。
 * 对标前端 axios 的请求拦截器：前端在发请求前给 header 塞 token；
 * 这里是后端在进 Controller 前，校验 token、提取 userId。
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 浏览器跨域预检请求（OPTIONS）不带 Authorization，必须放行，
        // 否则第 30 章配置的 CORS 在真实跨域下会被这里 401 拦死。
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new BizException(401, "未登录或 token 缺失");
        }
        String token = header.substring(7);
        try {
            Long userId = jwtUtil.getUserId(token);
            // 解析出的 userId 放进请求上下文，Controller 用 request.getAttribute("userId") 取
            request.setAttribute("userId", userId);
        } catch (Exception e) {
            throw new BizException(401, "token 无效或已过期");
        }
        return true;   // 放行
    }
}
