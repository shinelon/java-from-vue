# 枚举与包装类

## 枚举 enum

当你有一组**固定的取值**（订单状态、性别、角色），用枚举——比字符串常量安全得多。

```java
enum Status {
    PENDING("等待中"),
    RUNNING("运行中"),
    DONE("已完成");

    private final String label;
    Status(String label) { this.label = label; }
    public String getLabel() { return label; }
}

Status s = Status.RUNNING;
switch (s) { case RUNNING: ... }
```

**枚举的本质是个类**：可以有字段、构造器、方法。前端对照：比 JS 的 `Object.freeze({PENDING:'pending',...})` 强，因为它有类型、能挂方法、能 switch。

## 包装类

基本类型（`int`/`double`...）不是对象。需要对象形态时（比如集合里只能放对象、泛型要对象），用**包装类**：

| 基本类型 | 包装类 |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `long` | `Long` |
| `boolean` | `Boolean` |
| `char` | `Character` |

```java
Integer boxed = 20;          // 自动装箱 int → Integer
int n = boxed;               // 自动拆箱
int parsed = Integer.parseInt("123");    // 字符串转数字（超常用）
```

## ★ 坑：Integer 用 == 比较

```java
Integer a = 127, b = 127;
a == b;          // true（Integer 缓存了 -128~127，命中缓存）

Integer c = 200, d = 200;
c == d;          // false！超出缓存，是两个不同对象
c.equals(d);     // true ✓
```

**铁律：包装类比较内容一律用 `.equals()`，绝不用 `==`。** 这和第 04 章字符串的坑是同一个原理。

## 实战提示

- 数据库字段（状态、类型）映射成枚举，MyBatis-Plus 第三篇会用到。
- 前端传来的 `"123"` 字符串转数字，用 `Integer.parseInt`。
- 实体类的数值字段，`Integer`（可 null）比 `int`（默认 0）更合适——因为"未设置"和"0"是两回事。

## 完整可运行示例

```java
--8<-- "language/ch12-enum-wrapper/src/main/java/com/javaglm/language/ch12/EnumWrapperDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：异常处理](11-exceptions.md) ｜ 下一章：集合框架
