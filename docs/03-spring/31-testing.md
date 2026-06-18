# 接口测试

后端怎么验证自己写的对？两种：手动用 Postman 调，或写自动化测试。

## 手动测试：Postman / Apifox

1. 先 `POST /auth/register` 注册，再 `POST /auth/login` 拿 token；
2. Postman 里给后续请求统一配 Header `Authorization: Bearer <token>`；
3. `POST /tasks`、`GET /tasks` 逐个调，看返回对不对。

适合开发期快速验证。缺点：每次改代码都要手动重调一遍。

## 自动化测试：JUnit

写代码测代码，一次编写、随时运行。Spring Boot 自带 JUnit 5（`spring-boot-starter-test`）。

最实用的是**纯逻辑单元测试**——不启动 Spring、不连数据库，秒级跑完：

```java
--8<-- "task-manager/src/test/java/com/javaglm/task/TaskManagerApplicationTests.java"
```

要点：

- `@Test` 标记一个测试方法；
- `assertEquals(期望值, 实际值)` 断言相等，不等就失败；
- `assertTrue` / `assertFalse` 断言真假；
- 这个测试测了 `Result` 封装和 `BCryptPasswordEncoder`，都是纯逻辑，跑得飞快。

## 运行测试

```bash
cd code/task-manager
mvn test
```

```bash
# 只跑这一个测试类
mvn test -Dtest=TaskManagerApplicationTests
```

## 三种测试层次

| 类型 | 测什么 | 速度 | 本书 |
|---|---|---|---|
| **单元测试** | 纯逻辑（如上面的） | 极快 | ✅ 重点 |
| 切片测试（`@WebMvcTest`） | 只测 Web 层，Service 用 mock | 快 | 了解 |
| 集成测试（`@SpringBootTest`） | 启动整个应用 + 真实数据库 | 慢 | 少用 |

!!! tip "前端人对照"
    JUnit 对标前端的 Vitest / Jest。`@Test` = `it()`，`assertEquals` = `expect().toBe()`，`@BeforeEach` = `beforeEach()`。思想完全一样。

## 建议

- 工具方法（加密、转换、Result 封装）多写单元测试；
- 业务接口用 Postman 手动测为主；
- 关键流程（登录、下单）写一两个集成测试兜底。

---

[:octicons-arrow-left-16: 上一章：跨域 CORS](30-cors.md) ｜ [第四篇 · 全栈实战](../04-fullstack/32-integration-overview.md)
