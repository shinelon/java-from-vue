# 给 Vue 工程师的 Java 全栈教程

> 用 JS/TS/Vue 概念做桥梁，带前端工程师从 Java 语法走到能独立写 Spring Boot 后端，并完成全栈打通。
> 本教程自身用 **mkdocs + Material** 构建，代码示例**全部真实可编译**、文档直接引用嵌入、零漂移。

📦 **仓库地址**：<https://github.com/shinelon/java-from-vue> ｜ 发现问题欢迎提 Issue / PR

## 技术栈

| 维度 | 选型 |
|---|---|
| Java | JDK 8 风格（`--release 8`）+ 《从 8 到 17》新特性章 |
| 构建工具 | Maven |
| 后端框架 | Spring Boot 2.7.x（javax.*） |
| 数据库 / 持久层 | MySQL + MyBatis-Plus |
| 前端（全栈章） | Vue 3 + Vite + Pinia + Axios |
| 文档 | mkdocs + mkdocs-material |

## 目录结构

```
java-glm/
├── mkdocs.yml              # 文档配置
├── docs/                   # 教程章节（Markdown，按 5 篇分目录）
└── code/                   # 全部可运行代码
    ├── pom.xml             # 聚合根：mvn -f code/pom.xml compile 编译全部
    ├── language/           # 第二篇语言基础示例（ch04-ch19）
    ├── task-manager/       # 终局项目（Spring Boot 2.7 + MyBatis-Plus）
    ├── advanced/           # 第五篇进阶示例（ch36 · JDK 17）
    └── fullstack-frontend/ # 第四篇前端（Vue 3 + Vite + Pinia）
```

## 本地工作流

### 预览文档（热更新）

```bash
# 首次安装（已完成）
# python -m venv .venv
# .venv/Scripts/python -m pip install mkdocs mkdocs-material pymdown-extensions

# 启动预览，浏览器打开 http://127.0.0.1:8000
.venv/Scripts/python -m mkdocs serve
```

### 构建静态站点

```bash
.venv/Scripts/python -m mkdocs build       # 产物在 site/
```

### 验证代码可编译

```bash
# 编译全部可运行示例
mvn -f code/pom.xml compile

# 只编译某一章
mvn -f code/pom.xml -pl language/ch04-types -am compile

# 运行某章示例
cd code/language/ch04-types && mvn exec:java
```

## 写作约定

- **代码先于文档**：先在 `code/` 写可运行 `.java`，`mvn compile` 验证通过后，文档用 `--8<-- "..."` 引用。
- **类比优先**：每个概念给"Vue/JS 里相当于什么"。
- **够用即可**：不碰 JUC 源码 / JVM 调优 / 设计模式。

## 阅读教程

本地 `mkdocs serve` 后访问 [http://127.0.0.1:8000](http://127.0.0.1:8000)，从《第一篇 · 准备与地图》开始。
