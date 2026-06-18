# 跨域 CORS

前端 `localhost:5173` 调后端 `localhost:8080`，浏览器会报跨域错误。这一章解决它。

## 什么是跨域

浏览器的**同源策略**：协议、域名、端口任一不同，就是跨域，默认不让 JS 读响应。

```
前端：http://localhost:5173
后端：http://localhost:8080     ← 端口不同，跨域！
```

报错长这样：`Access-Control-Allow-Origin` 缺失。

## 谁来解决

**让后端允许**跨域（推荐），前端就不用管。Spring 里加个配置：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/config/CorsConfig.java"
```

关键配置：

| 项 | 含义 |
|---|---|
| `addMapping("/**")` | 所有接口都允许跨域 |
| `allowedOriginPatterns("*")` | 允许哪些来源（生产应收紧成具体域名） |
| `allowedMethods(...)` | 允许的 HTTP 方法 |
| `allowCredentials(true)` | 允许带 Cookie / Authorization 头 |
| `maxAge(3600)` | 预检请求缓存时间 |

## 预检请求 OPTIONS

非简单请求（如带 `Authorization` 头的）浏览器会先发一个 `OPTIONS` 请求"问一下"后端允不允许，叫**预检**。配置好 CORS 后，Spring 自动正确响应 OPTIONS，前端无感。

!!! warning "预检请求会被拦截器拦住！这是跨域最大的坑"
    预检 OPTIONS **不带 `Authorization`**。如果你还注册了 JWT 拦截器，它默认也会拦截 OPTIONS → 直接抛 401，浏览器收不到 CORS 响应头 → 跨域失败。表现就是：你明明配了 CORS，前端还是报跨域错误。

    **解法**：让拦截器对 OPTIONS 放行。本项目 `JwtInterceptor` 第一行就是这么做的（见上一章 JWT 认证）。前后端分离最容易踩的坑之一。

## 前端要不要配

后端配了 CORS，前端 axios 直接调就行。开发时 Vite 也可以配代理（第四篇讲）作为另一种方案——但**生产环境必须后端配 CORS**，Vite 代理只在开发有效。

!!! tip "跨域 vs 认证"
    跨域（浏览器不让请求）和认证（后端不让访问）是两回事。先解决跨域（CORS），请求才能到达后端；再解决认证（JWT）。

---

[:octicons-arrow-left-16: 上一章：JWT 认证](29-jwt-auth.md) ｜ 下一章：接口测试
