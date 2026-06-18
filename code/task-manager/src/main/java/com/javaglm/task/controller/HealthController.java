package com.javaglm.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康检查接口 · 骨架。
 *
 * <p>对标前端：{@code @RestController} 就像一个"路由 + 自动把返回值转成 JSON"的处理器。
 * 访问 GET http://localhost:8080/health 会拿到一段 JSON。
 *
 * <p>真实的任务 CRUD / 登录认证接口在第三篇第 28、29 章逐步实现。
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "task-manager");
        return result;
    }
}
