package com.javaglm.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置（第 30 章）。
 * 浏览器同源策略会拦截"前端 localhost:5173 → 后端 localhost:8080"的请求。
 * 这里在后端统一放行，前端就不用每个接口单独处理跨域了。
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")                          // 允许所有来源（生产应收紧）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)                              // 允许带 Cookie / Authorization
                .maxAge(3600);                                       // 预检请求缓存 1 小时
    }
}
