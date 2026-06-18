# Lambda 与函数式接口

Lambda 就是 Java 版的**箭头函数**。这是前端人最该觉得亲切的一章——语法几乎一一对应。

## Lambda 语法

```java
() -> 表达式               // 无参
x -> x * 2                 // 单参（可省括号）
(x, y) -> x + y            // 多参
(x, y) -> { int z = x+y; return z; }   // 多行用大括号 + return
```

对标 JS：

```js
() => 表达式
x => x * 2
(x, y) => x + y
```

## 函数式接口

Lambda 不是凭空存在的——它必须赋值给一个**只有一个抽象方法的接口**（函数式接口）。

```java
interface StringProcessor {
    String process(String s);
}
StringProcessor upper = s -> s.toUpperCase();   // lambda 赋值
upper.process("hi");   // HI
```

## JDK 自带的四大函数式接口

不用自己定义，`java.util.function` 包里有现成的（对标 TS 的函数类型）：

| 接口 | 签名 | 对标 TS | 用途 |
|---|---|---|---|
| `Function<T,R>` | `T → R` | `(x: T) => R` | 转换 |
| `Predicate<T>` | `T → boolean` | `(x: T) => boolean` | 判断/过滤 |
| `Consumer<T>` | `T → void` | `(x: T) => void` | 消费（打印等） |
| `Supplier<T>` | `() → T` | `() => T` | 生产/提供 |

```java
Function<String, Integer> length = s -> s.length();
Predicate<String> isLong = s -> s.length() > 3;
Consumer<String> printer = s -> System.out.println(s);
Supplier<String> supplier = () -> "默认值";
```

## 方法引用

当 lambda 只是调用某个已有方法时，可以简化成**方法引用** `类名::方法名`：

```java
names.forEach(s -> System.out.println(s));   // 完整 lambda
names.forEach(System.out::println);          // 方法引用，等价
```

对标 JS：把函数当值传递，`arr.forEach(console.log)`。

## 为什么现在才讲 Lambda

JDK 8 最大的改进就是引入 Lambda。它让集合操作（下一章 Stream）变得极其优雅。Spring 里到处是 `Function`/`Predicate` 参数，会读 Lambda 是读懂现代 Java 的前提。

## 完整可运行示例

```java
--8<-- "language/ch15-lambda/src/main/java/com/javaglm/language/ch15/LambdaDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：泛型](14-generics.md) ｜ 下一章：Stream API
