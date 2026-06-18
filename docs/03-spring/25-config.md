# 配置管理

数据库账号、端口号、JWT 密钥这些"会变的东西"，不能硬编码在 Java 里，要放配置文件。

## application.yml

Spring Boot 默认读 `src/main/resources/application.yml`（或 `.properties`）。本书 task-manager 的配置：

```yaml
--8<-- "task-manager/src/main/resources/application.yml"
```

对标前端：像 `.env` 文件 + Vite 的 `import.meta.env`。YAML 用缩进表示层级，比 properties 直观。

## 读配置：@Value

Java 代码里用 `@Value("${键名}")` 注入配置值。`JwtUtil` 读 JWT 密钥和过期时间就是这么干的：

```java
@Value("${jwt.secret}")
private String secret;

@Value("${jwt.expire-millis:86400000}")   // 冒号后是默认值
private long expireMillis;
```

完整代码：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/security/JwtUtil.java"
```

## 多环境：profiles

开发/测试/生产用不同配置（开发连本地库，生产连线上库）。拆成多个文件：

- `application.yml` —— 公共配置
- `application-dev.yml` —— 开发环境
- `application-prod.yml` —— 生产环境

激活某个环境：

```yaml
# application.yml
spring:
  profiles:
    active: dev   # 当前用 dev 配置
```

或启动时指定：`java -jar app.jar --spring.profiles.active=prod`。

!!! danger "敏感配置别提交"
    数据库密码、JWT 密钥这类敏感信息，生产环境用**环境变量**注入，不要明文写进 yml 提交到 git。
    `@Value("${DB_PASSWORD}")` 会自动读同名环境变量。

## 常用配置项速查

| 配置 | 作用 |
|---|---|
| `server.port` | 服务端口 |
| `spring.datasource.*` | 数据库连接 |
| `spring.profiles.active` | 当前环境 |
| `logging.level.*` | 日志级别 |
| `mybatis-plus.*` | MyBatis-Plus 配置 |

---

[:octicons-arrow-left-16: 上一章：统一响应与全局异常](24-response-exception.md) ｜ 下一章：MyBatis-Plus 数据访问
