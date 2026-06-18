# 第一个 Spring Boot 项目

亲手创建一个 Spring Boot 项目，跑通第一个接口。

## 创建项目的两种方式

**方式一：start.spring.io（网页生成）**

1. 打开 [start.spring.io](https://start.spring.io/)；
2. 选 Maven、Java 8、Spring Boot 2.7.18；
3. 添加依赖：Spring Web、Validation、MySQL Driver；
4. 生成下载，解压即用。

**方式二：IDEA**

`New Project → Spring Initializr`，同样的选项，IDEA 里点几下就行。

> 本书的 `code/task-manager/` 就是这样一个项目，可以直接用。

## pom.xml：依赖清单

对标前端的 `package.json`。本书 task-manager 的关键依赖：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.18</version>          <!-- Spring Boot 版本，统一管理所有子依赖版本 -->
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>   <!-- Web：REST + 内置 Tomcat -->
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>  <!-- 参数校验 -->
    </dependency>
    <!-- MyBatis-Plus、MySQL、JWT 等见各自章节 -->
</dependencies>
```

!!! info "starter 是什么"
    `spring-boot-starter-web` 是个"大礼包"，一个依赖拉进来 Web 开发要的一堆东西（Tomcat、Spring MVC、Jackson）。对标前端：像 `@vue/xxx` 这种把相关功能打包好的依赖。

## 启动类

一个 Spring Boot 应用就从这个 `main` 方法启动：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/TaskManagerApplication.java"
```

- `@SpringBootApplication` = 自动配置 + 组件扫描 + 启动入口，三合一。
- 对标前端：相当于 `createApp(App).mount('#app')` 的启动点。

## 第一个接口

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/controller/HealthController.java"
```

- `@RestController` = 这个类处理 HTTP 请求，返回值自动转 JSON。
- `@GetMapping("/health")` = 注册一个 `GET /health` 路由。对标前端的 Express/Koa 路由。
- 返回 `Map`，Spring 自动用 Jackson 序列化成 JSON。

## 运行

```bash
cd code/task-manager
mvn spring-boot:run
```

浏览器或 Postman 访问 `http://localhost:8080/health`，会看到：

```json
{ "status": "UP", "service": "task-manager" }
```

!!! warning "首次运行需要数据库"
    完整的 task-manager 配了 MySQL 数据源（第 25 章）。如果还没建库，启动会报连接错误。
    先按第 27 章建好 `task_manager` 库和表，改好 `application.yml` 的密码，再运行。

---

[:octicons-arrow-left-16: 上一章：IoC 与 DI](20-ioc-di.md) ｜ 下一章：REST 与分层架构
