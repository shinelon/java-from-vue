# 流程控制

好消息：Java 的 `if` / `for` / `while` 和 JavaScript **几乎一模一样**，前端人这部分可以极速迁移。
本章只讲**差异点**和**易错点**。

## if / else if / else

语法和 JS 完全相同。唯一要记住的是：**条件表达式必须是 `boolean`**——Java 不会像 JS 那样做隐式类型转换。

```java
int count = 0;
if (count) { }       // ✗ 编译报错！Java 不把数字当布尔
if (count > 0) { }   // ✓
```

## switch

```java
String day = "MON";
switch (day) {
    case "MON": System.out.println("周一"); break;   // 别忘 break，否则会"穿透"
    case "SUN": System.out.println("周日"); break;
    default: System.out.println("工作日");
}
```

!!! info "switch 表达式在哪？"
    JS 有 `switch` 表达式，但 **Java 8 没有**（switch 表达式是 JDK 14 才有的）。
    这里只能用"语句"。现代写法见《第五篇 · 从 JDK 8 到 17》。

## for 循环

```java
// 经典 for：和 JS 一样
for (int i = 0; i < 3; i++) { ... }

// 增强型 for（for-each）：对应 JS 的 for...of，注意是冒号不是 of
int[] nums = {10, 20, 30};
for (int n : nums) {
    System.out.println(n);
}
```

## 完整可运行示例

```java
--8<-- "language/ch05-control-flow/src/main/java/com/javaglm/language/ch05/ControlFlowDemo.java"
```

## 前端人对照

| JS | Java | 备注 |
|---|---|---|
| `for (const x of arr)` | `for (int x : arr)` | 冒号 `:` |
| `for (const k in obj)` | 没有直接等价 | 遍历对象用 `Map` 的方法 |
| `if (count) {}` | `if (count > 0) {}` | Java 不隐式转布尔 |
| `switch` 表达式 | JDK 8 没有 | 新特性章讲 |

---

[:octicons-arrow-left-16: 上一章：基本类型与变量](04-types-variables.md) ｜ 下一章：方法与数组
