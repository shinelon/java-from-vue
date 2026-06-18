package com.javaglm.task.controller;

import com.javaglm.task.common.Result;
import com.javaglm.task.dto.LoginRequest;
import com.javaglm.task.dto.RegisterRequest;
import com.javaglm.task.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 认证接口（第 29 章）：注册 / 登录。
 * 这两个路径在 WebMvcConfig 里被 JWT 拦截器放行（不用登录就能访问）。
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody @Valid RegisterRequest req) {
        authService.register(req);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody @Valid LoginRequest req) {
        return Result.success(authService.login(req));
    }
}
