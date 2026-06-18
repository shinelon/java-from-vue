package com.javaglm.task.security;

import com.javaglm.task.common.BizException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JWT 拦截器单元测试（第 29/30 章）。
 * 重点：浏览器跨域预检请求（OPTIONS）不带 Authorization，拦截器必须放行，
 * 否则第 30 章配置的 CORS 在真实跨域下会被 401 拦死。
 */
class JwtInterceptorTest {

    private final JwtUtil jwtUtil = Mockito.mock(JwtUtil.class);
    private final JwtInterceptor interceptor = new JwtInterceptor(jwtUtil);

    @Test
    void optionsPreflightShouldPassWithoutToken() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getMethod()).thenReturn("OPTIONS");
        // 预检请求本就不带 Authorization 头，故意不 stub

        assertTrue(interceptor.preHandle(request, response, new Object()));
        Mockito.verifyNoInteractions(jwtUtil);   // OPTIONS 不应触发 JWT 校验
    }

    @Test
    void getWithoutTokenShouldStillBeRejected() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        try {
            interceptor.preHandle(request, response, new Object());
            fail("缺少 token 的普通请求应被拒绝");
        } catch (BizException e) {
            assertTrue(e.getCode() == 401);
        }
    }
}
