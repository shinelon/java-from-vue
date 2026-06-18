package com.javaglm.task.service;

import com.javaglm.task.dto.LoginRequest;
import com.javaglm.task.dto.RegisterRequest;

import java.util.Map;

/** 认证服务接口（第 29 章）：注册 + 登录。 */
public interface AuthService {

    void register(RegisterRequest req);

    /** 登录成功返回 token 和用户信息。 */
    Map<String, Object> login(LoginRequest req);
}
