# 基本类型与变量

从最基础的开始。这一章你会建立 Java 的**强类型心智**——它和 JS 最大的体感差异。

## Java 的 8 种基本类型

JS 只有 `number`（什么整数小数都塞进去）。Java 把数值**拆得很细**，每种占的内存不同：

| 类型 | 含义 | 范围 | JS 对照 |
|---|---|---|---|
| `byte` | 8 位整数 | -128 ~ 127 | 无 |
| `short` | 16 位整数 | ±3.2 万 | 无 |
| **`int`** | 32 位整数（最常用） | ±21 亿 | `number` 的整数部分 |
| **`long`** | 64 位整数 | 极大 | `number` |
| `float` | 32 位浮点 | — | `number` |
| **`double`** | 64 位浮点（默认小数） | — | `number` 的小数部分 |
| `char` | 单个字符 | `'A'` | 长度为 1 的 string |
| `boolean` | 真 / 假 | `true` / `false` | `boolean` |

!!! tip "前端人记忆法"
    平时写整数用 `int`，写大数用 `long`（字面量加 `L`：`10000000000L`），
    写小数用 `double`，判断真假用 `boolean`。这就够覆盖 90% 场景。

## 变量：类型必须写在前面

```js
// JS：类型藏在值里
let count = 10;        // 不写类型
count = "十";          // 合法，类型变了
```

```java
// Java：类型写在变量名前面
int count = 10;        // 必须写 int
count = "十";          // ✗ 编译报错！int 永远不能装字符串
```

**类型一旦声明就锁死**，编译器在编译期就检查——这正是 Java "啰嗦但安全"的根源。

## 引用类型：String

基本类型之外的值都是**引用类型**（对象），最常见的是 `String`：

```java
String name = "Tom";        // 双引号是字符串，单引号是 char
char first = 'T';           // 单引号只能放一个字符
```

## 一个可运行的完整示例

下面这段代码是仓库里**真实可编译**的文件 `TypesDemo.java`——文档直接引用它的内容，
改了代码这里自动同步：

```java
--8<-- "language/ch04-types/src/main/java/com/javaglm/language/ch04/TypesDemo.java"
```

!!! note "上面这段代码是怎么进来的"
    Markdown 里只写了一行 `--8<-- "language/ch04-types/.../TypesDemo.java"`，
    mkdocs 渲染时把那个真实 `.java` 文件的内容**原样嵌入**。这就是"所见即所编译"。

## 想自己跑这段代码

```bash
cd code/language/ch04-types
mvn compile exec:java -Dexec.mainClass="com.javaglm.language.ch04.TypesDemo"
```

或在 IDEA 里打开 `code/` 目录，找到 `TypesDemo.java`，点旁边的绿色运行按钮。

---

下一章（编写中）：流程控制。先看 [第二篇路线图](roadmap.md)。
