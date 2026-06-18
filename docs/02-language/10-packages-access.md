# 包与访问修饰符

## 包 package = 目录

```java
package com.javaglm.language.ch10;   // 声明本类属于哪个包
```

**铁律：包名必须和目录结构一一对应。** 文件在 `com/javaglm/language/ch10/` 目录下，包名就必须是 `com.javaglm.language.ch10`。对标 ES module：包就像文件路径。

## import 引入别的包

```java
import com.javaglm.language.ch10.model.Product;   // 精确导入一个类
import java.util.*;                                // 导入整个包（少用，IDE 一般帮你精确导入）
```

对标 JS 的 `import { Product } from './model/Product'`。区别：Java 默认 `java.lang` 包（String、System 等）自动导入，不用写。

## 四种访问修饰符

| 修饰符 | 本类 | 同包 | 子类 | 其他包 | 对标 |
|---|:---:|:---:|:---:|:---:|---|
| `public` | ✅ | ✅ | ✅ | ✅ | export |
| `protected` | ✅ | ✅ | ✅ | ❌ | — |
| 不写（default） | ✅ | ✅ | ❌ | ❌ | 模块内部 |
| `private` | ✅ | ❌ | ❌ | ❌ | #私有字段 |

**记忆法**：从宽到窄 `public > protected > default > private`。

## 实战建议

- **字段一律 `private`**，通过 getter/setter 暴露（封装，第 08 章）。
- **类一般 `public`**（一个文件一个 public 类）。
- 工具方法/常量用 `public static`。
- Spring 里，Controller / Service / Mapper 的实现类通常 `public`，接口 `public`。

## 完整可运行示例

主类（演示同包访问 + 跨包 import）：

```java
--8<-- "language/ch10-packages-access/src/main/java/com/javaglm/language/ch10/PackageDemo.java"
```

跨包的被引用类：

```java
--8<-- "language/ch10-packages-access/src/main/java/com/javaglm/language/ch10/model/Product.java"
```

!!! tip "包命名约定"
    公司域名反写开头：`com.公司名.项目名.模块`。本书统一用 `com.javaglm.*`。

---

[:octicons-arrow-left-16: 上一章：接口与抽象类](09-interface-abstract.md) ｜ 下一章：异常处理
