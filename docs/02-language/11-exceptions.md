# 异常处理

`try/catch/finally` 你在 JS 里写过，Java 几乎一样。但 Java 多一个**前端人完全陌生的概念：受检异常**。

## 基本结构（和 JS 一样）

```java
try {
    riskyCode();
} catch (NumberFormatException e) {   // 捕获特定异常
    System.out.println(e.getMessage());
} finally {
    cleanup();   // 无论如何都执行
}
```

## ★ 受检 vs 非受检（这是新概念）

| 类型 | 继承 | 特点 | 例子 |
|---|---|---|---|
| **受检异常** | `Exception` | 编译器**强制** try/catch 或 throws，否则编译不过 | `IOException`、`SQLException` |
| **非受检异常** | `RuntimeException` | 和 JS 一样，可不处理 | `NullPointerException`、`IllegalArgumentException` |

```java
// readFile 抛受检异常，下面这行不处理就编译报错：
readFile();        // ✗ Unhandled exception BizException

// 必须这样：
try { readFile(); }
catch (BizException e) { ... }

// 或者接着往上甩：
void caller() throws BizException { readFile(); }
```

!!! info "为什么要搞受检异常"
    JS 的异常你忘了 catch 也不会报错，运行时才炸。Java 的受检异常让编译器**逼着你面对**可能出错的情况（比如 IO、数据库操作），减少了运行时意外。这是 Java"啰嗦但安全"的又一体现。

## throws 和 throw

- `throw new XxxException()` —— 主动抛出（对标 JS `throw`）。
- `void f() throws XxxException` —— 声明"我可能抛这个异常，调用方要处理"。

## 自定义异常

```java
class BizException extends Exception {       // 受检
    BizException(String msg) { super(msg); }
}
```

业务里常用：自定义 `BizException` 表示"业务错误"（如余额不足），在 Spring 第三篇你会用全局异常处理器统一接住它。

## 完整可运行示例

```java
--8<-- "language/ch11-exceptions/src/main/java/com/javaglm/language/ch11/ExceptionDemo.java"
```

## 前端人对照

| JS | Java | 备注 |
|---|---|---|
| `try/catch/finally` | 同 | 几乎一致 |
| `throw new Error()` | `throw new XxxException()` | Java 异常是类 |
| 无受检/非受检之分 | 有 | 受检异常强制处理 |
| `catch (e)` 抓所有 | `catch (Exception e)` | Java 要写异常类型 |

---

[:octicons-arrow-left-16: 上一章：包与访问修饰符](10-packages-access.md) ｜ 下一章：枚举与包装类
