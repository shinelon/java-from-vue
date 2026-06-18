# 开发环境搭建

本篇带你装好写 Java 后端所需的全部工具。**全程用前端人熟悉的 npm 心智做对照**，
你会发现 Java 的工具链其实并不神秘。

## 需要安装的工具

| 工具 | 作用 | 前端对照 |
|---|---|---|
| **JDK** | Java 运行时 + 编译器 | Node.js 运行时 |
| **Maven** | 依赖管理 + 构建工具 | npm / pnpm |
| **MySQL** | 数据库 | 浏览器 localStorage？不，更像服务端的数据存储 |
| **IntelliJ IDEA** | IDE（强烈推荐） | VS Code（但 IDEA 对 Java 的支持远超 VS Code） |
| **Postman / Apifox** | 测接口 | 你前端调接口时用的工具 |

---

## 1. 安装 JDK

!!! note "为什么是 JDK 而不是 JRE"
    JRE 只能**运行** Java 程序，JDK 还能**编译**。我们要写代码，必须装 JDK。

- 推荐下载 **Oracle JDK 17** 或 **OpenJDK 17**（[adoptium.net](https://adoptium.net/)）。
- 安装后配置环境变量 `JAVA_HOME`，并把 `%JAVA_HOME%\bin` 加入 `PATH`。
- 验证：

```bash
java -version
# java version "17.0.x" ...
```

!!! tip "JDK 版本说明"
    本书代码以 **Java 8 风格**为基线。装 JDK 17 完全没问题——Maven 会用 `--release 8`
    把它编译成 Java 8 字节码。详见 [首页 · 关于本机 JDK 版本](../index.md)。

## 2. 安装 Maven

Maven 就是 Java 世界的 **npm**：你声明要哪些依赖（写在 `pom.xml`，相当于 `package.json`），
它自动去中央仓库（相当于 npm registry）下载。

- 下载 [Apache Maven](https://maven.apache.org/download.cgi)，解压，把 `bin` 目录加入 `PATH`。
- 验证：

```bash
mvn -version
# Apache Maven 3.6.x ...
```

!!! warning "国内加速必做：换镜像源"
    Maven 默认从国外中央仓库下依赖，国内巨慢。在 `~/.m2/settings.xml` 里换成阿里云镜像：
    ```xml
    <mirrors>
      <mirror>
        <id>aliyun</id>
        <mirrorOf>central</mirrorOf>
        <url>https://maven.aliyun.com/repository/public</url>
      </mirror>
    </mirrors>
    ```
    这相当于把 npm 换成 `npmmirror.com`。

!!! tip "想深入理解 Maven？"
    Maven 的生命周期、依赖范围（scope）、多模块聚合这些 npm 没有的概念，见 [Maven 必备](maven-essentials.md)。建议在进入第二篇敲代码前读一遍。

## 3. 安装 MySQL

- 下载 [MySQL Community Server](https://dev.mysql.com/downloads/)，安装时记住 root 密码。
- 配一个图形客户端（DBeaver / Navicat / DataGrip）方便看数据。

## 4. 安装 IntelliJ IDEA

- 下载 [IDEA Community](https://www.jetbrains.com/idea/download/)（免费版够用）或 Ultimate（学生/开源免费）。
- 打开本项目 `code/` 目录，IDEA 会自动识别 Maven 工程并下载依赖。

---

## 跑起来：本教程的本地预览

本教程本身用 mkdocs 构建。在**仓库根目录**执行：

```bash
# 首次：创建虚拟环境并安装依赖（已由项目脚本完成，这里说明原理）
python -m venv .venv
.venv/Scripts/python -m pip install mkdocs mkdocs-material pymdown-extensions

# 启动本地预览（热更新）
.venv/Scripts/python -m mkdocs serve
# 浏览器打开 http://127.0.0.1:8000
```

| 概念 | Java 世界 | 前端世界 |
|---|---|---|
| 依赖清单 | `pom.xml` | `package.json` |
| 已下载依赖 | `~/.m2/repository` | `node_modules` |
| 安装依赖 | `mvn` 自动 | `npm install` |
| 运行/构建 | `mvn compile` / `mvn package` | `npm run build` |
| 包仓库 | Maven Central | npm registry |

---

下一篇：[Vue/JS 工程师的 Java 速查表](03-cheatsheet.md)
