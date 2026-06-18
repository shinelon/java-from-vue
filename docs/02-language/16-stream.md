# Stream API

如果你熟悉 JS 的 `[1,2,3].filter(...).map(...)`，Java 的 Stream 你会**直接上手**——这是后端开发里前端人优势最明显的部分。

## 三步走

```java
集合.stream()        // 1. 创建流
     .filter(...)    // 2. 中间操作（可串多个，惰性：不触发执行）
     .map(...)       //    中间操作
     .collect(...)   // 3. 终止操作（触发真正执行，产出结果）
```

## 与 JS 数组方法对照

| JS | Java Stream | 说明 |
|---|---|---|
| `.filter(x => x>3)` | `.filter(x -> x>3)` | 过滤 |
| `.map(x => x*2)` | `.map(x -> x*2)` | 映射 |
| `.reduce((a,b)=>a+b, 0)` | `.reduce(0, (a,b)->a+b)` | 归约 |
| `.length` | `.count()` | 计数（终止操作） |
| `.forEach(x=>...)` | `.forEach(x -> ...)` | 遍历 |

## 完整链式示例

```java
List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)     // 偶数
        .map(n -> n * 10)            // ×10
        .collect(Collectors.toList());   // 收集成 List
// [20, 40, 60]
```

## 关键差异

!!! warning "Stream 不是数组"
    - JS 的 `.filter()` 直接返回新数组。Java 的 Stream **中间操作是惰性的**，必须有个**终止操作**（`collect`/`count`/`forEach`）才会真正执行。
    - 收集成 List 要 `.collect(Collectors.toList())`，不能直接当 List 用。
    - Stream **只能消费一次**（流过即逝），不像数组能反复遍历。

## Optional：优雅处理"可能没有"

```java
Integer first = nums.stream()
        .filter(n -> n > 10)
        .findFirst()        // 返回 Optional<Integer>
        .orElse(null);      // 找不到时兜底（对标 JS 的 ?? / ||）
```

`Optional` 是 Java 用来避免 `NullPointerException` 的容器——"可能有值也可能没有"。第三篇业务代码会常用。

## 实战

Stream 在后端处理集合数据时极其常用：把 `List<Entity>` 转成 `List<VO>`、过滤、分组、统计。MyBatis-Plus 查出来的数据经常配合 Stream 加工后返回前端。

## 完整可运行示例

```java
--8<-- "language/ch16-stream/src/main/java/com/javaglm/language/ch16/StreamDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：Lambda](15-lambda.md) ｜ 下一章：IO 与文件基础
