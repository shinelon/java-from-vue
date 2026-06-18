# 方法与数组

## 方法（method）

Java 的方法对比 JS 函数，有几个硬规矩：

1. **必须声明返回类型**（不返回就写 `void`）；
2. **必须挂在类里**——没有顶层函数；
3. **参数必须写类型**；
4. 支持**方法重载**（同名、参数列表不同）——JS 没有这个。

```java
static int add(int a, int b) {   // 返回 int，两个 int 参数
    return a + b;
}
static int add(int a, int b, int c) {  // 重载：三个参数
    return a + b + c;
}
```

!!! tip "`static` 是什么"
    `static` 方法属于"类本身"，不依赖对象就能调用。对标 JS：就像挂在对象/模块上的普通函数。
    第 07 章讲对象后，你会看到"实例方法"（不带 static）。`main` 方法必须是 static，因为程序启动时还没有对象。

## 可变参数 varargs

```java
static int sum(int... values) { }   // int... 等价于 int[]
sum(1, 2, 3, 4);                    // 随便传几个
```

对标 JS 的剩余参数 `function sum(...values)`。

## 数组

```java
int[] arr = new int[3];          // 定长 3，元素默认 0
String[] names = {"Tom", "Jerry"};   // 创建并赋值
arr.length;                      // 长度（属性，不是方法）
Arrays.toString(arr);            // 打印内容 [10, 20, 0]
Arrays.sort(arr);                // 排序
```

!!! warning "数组是定长的"
    Java 数组**创建后长度不可变**。JS 的 `Array` 能随意 push，Java 数组不行。
    要"动态长度"，用 `List`（第 13 章）或 `ArrayList`。

## 完整可运行示例

```java
--8<-- "language/ch06-methods-arrays/src/main/java/com/javaglm/language/ch06/MethodsArraysDemo.java"
```

## 前端人对照

| JS | Java | 备注 |
|---|---|---|
| `function add(a,b){}` | `int add(int a,int b){}` | 返回类型 + 参数类型 |
| 重载（不支持） | 真正的重载 | 按参数列表区分 |
| `...rest` | `int... values` | 可变参数 |
| `[1,2,3]`（可变长） | `int[]`（定长） | 动态长度用 List |
| `arr.length` | `arr.length` | 都是属性 |
| `arr.push()` | 不存在 | 用 `List.add()` |

---

[:octicons-arrow-left-16: 上一章：流程控制](05-control-flow.md) ｜ 下一章：面向对象基础
