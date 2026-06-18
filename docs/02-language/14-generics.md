# 泛型

写过 TypeScript 泛型？Java 泛型你会觉得毫无压力。`<T>` 就是类型参数，用的时候才确定具体类型。

## 泛型集合

```java
List<String> names = new ArrayList<>();   // <> 钻石操作符：右边类型可省
names.add("Tom");
// names.add(123);   // ✗ 编译报错
String s = names.get(0);    // 拿出来就是 String，不用强转
```

没有泛型的年代，集合放 `Object`，取出来要强转、容易 `ClassCastException`。泛型把这个错误提前到编译期。

## 自定义泛型类

```java
class Box<T> {
    private T value;
    public T get() { return value; }
    public void set(T value) { this.value = value; }
}

Box<Integer> intBox = new Box<>(99);
Box<String>  strBox = new Box<>("hi");
```

对标 TS 的 `class Box<T> {}`。

## 泛型方法

```java
static <T> T firstOf(List<T> list) {     // <T> 声明类型参数
    return list.get(0);
}
```

对标 TS 的 `function firstOf<T>(list: T[]): T`。

## 常用约定字母

| 字母 | 含义 | 常见场景 |
|---|---|---|
| `T` | Type 类型 | 通用 |
| `E` | Element 元素 | 集合 `List<E>` |
| `K`/`V` | Key / Value | Map `Map<K,V>` |
| `R` | Return 返回 | 函数式接口 |

## Spring 里你会见到的

- `List<Task>` —— 任务列表
- `ResponseEntity<T>` —— HTTP 响应包装
- MyBatis-Plus 的 `BaseMapper<T>`、`IService<T>` —— 泛型限定实体类型

泛型是第三篇读懂 Spring 代码的钥匙之一。

## 完整可运行示例

```java
--8<-- "language/ch14-generics/src/main/java/com/javaglm/language/ch14/GenericDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：集合框架](13-collections.md) ｜ 下一章：Lambda 与函数式接口
