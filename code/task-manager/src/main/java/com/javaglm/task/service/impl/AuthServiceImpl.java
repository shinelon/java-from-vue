package com.javaglm.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaglm.task.common.BizException;
import com.javaglm.task.dto.LoginRequest;
import com.javaglm.task.dto.RegisterRequest;
import com.javaglm.task.entity.User;
import com.javaglm.task.mapper.UserMapper;
import com.javaglm.task.security.JwtUtil;
import com.javaglm.task.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务实现（第 29 章）。
 * 注册：用户名查重 + BCrypt 哈希存密码。
 * 登录：校验密码 + 签发 JWT。
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /** 构造器注入（推荐）：依赖一目了然，对标前端的依赖注入思想。 */
    public AuthServiceImpl(UserMapper userMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest req) {
        Long count = userMapper.selectCount(
                new LambdaQueryWrapper<User>().eq(User::getUsername, req.getUsername()));
        if (count > 0) {
            throw new BizException("用户名已被占用");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));   // 只存哈希
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public Map<String, Object> login(LoginRequest req) {
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, req.getUsername()));
        if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BizException(401, "用户名或密码错误");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        result.put("user", userInfo);
        return result;
    }
}
