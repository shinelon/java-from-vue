# 参数接收与校验

前端调接口时，参数怎么传、后端怎么接、怎么校验——这一章讲清。

## 三种接收参数的方式

```java
// 1. @RequestParam：查询参数  GET /tasks?current=1&size=10
@RequestParam(defaultValue = "1") long current

// 2. @PathVariable：路径变量  DELETE /tasks/123
@PathVariable Long id

// 3. @RequestBody：请求体（JSON）  POST /tasks  { "title":"..." }
@RequestBody TaskRequest req
```

对标前端：就是你发请求时 URL 上的 `?xxx`、路径里的 `:id`、body 里的 JSON，一一对应。

## 接收 JSON：DTO

前端 POST 一个 JSON，后端用一个 **DTO（Data Transfer Object）** 接收：

```java
public class TaskRequest {
    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题最长 100 字")
    private String title;
    private String description;
    private Integer status;
}
```

## 参数校验：@Valid

DTO 上的 `@NotBlank`、`@Size` 是**校验注解**。但光标了没用，要在 Controller 参数上加 `@Valid` 才会触发：

```java
@PostMapping
public Result<Task> create(@RequestBody @Valid TaskRequest req) { ... }
//                            ↑ 加了它，框架自动校验 req 里的 @NotBlank 等
```

校验不通过时，框架抛 `MethodArgumentNotValidException`，被全局异常处理器（第 24 章）接住，自动返回：

```json
{ "code": 400, "message": "标题不能为空", "data": null }
```

前端拿到的就是干净的错误提示，不用自己判断。

## 常用校验注解

| 注解 | 作用 |
|---|---|
| `@NotBlank` | 字符串非 null 且非空 |
| `@NotNull` | 非 null |
| `@Size(min,max)` | 长度范围 |
| `@Min` / `@Max` | 数值范围 |
| `@Email` | 邮箱格式 |
| `@Pattern(regexp)` | 正则匹配 |

## 实际代码

任务请求 DTO（带校验）：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/dto/TaskRequest.java"
```

注册请求 DTO（用户名密码长度校验）：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/dto/RegisterRequest.java"
```

Controller 里用 `@Valid` 触发校验（看 create / update 方法）：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/controller/TaskController.java"
```

## 前端人对照

| 前端做法 | Java 做法 |
|---|---|
| 前端 form 校验（element-plus rules） | 后端 DTO 校验注解 + @Valid |
| 校验失败 toast 提示 | 返回 `{ code:400, message:"..." }` |
| 前端校验只是体验 | **后端校验是底线**（前端可绕过，后端不能省） |

!!! danger "前后端都要校验"
    前端校验是为了"用户体验"，后端校验是为了"安全"。前端校验可以被绕过（直接用 Postman 发请求），所以**后端校验绝不能省**。

---

[:octicons-arrow-left-16: 上一章：REST 与分层架构](22-rest-layering.md) ｜ 下一章：统一响应与全局异常
