# 集合框架

第 06 章说过：Java 数组是定长的。实际开发 99% 用的是**集合**。三个主角：`List`、`Set`、`Map`。

## 三大集合

| Java | 对标 JS | 特点 |
|---|---|---|
| `List` (`ArrayList`) | `Array` | 有序、可重复、按下标访问 |
| `Set` (`HashSet`) | `Set` | 不重复 |
| `Map` (`HashMap`) | `Map` / 对象 | 键值对，键不重复 |

## List

```java
List<String> list = new ArrayList<>();   // 接口 + 实现类
list.add("Tom");          // 末尾添加
list.get(0);              // 按下标取
list.remove("Tom");       // 删除
list.size();              // 长度（方法，不是属性）
list.contains("Tom");     // 是否包含
```

!!! info "为什么写 List 不是 ArrayList"
    `List` 是**接口**，`ArrayList` 是**实现**。变量声明用接口类型（`List`），`new` 用实现（`ArrayList`）。这样以后想换实现（如 `LinkedList`）只改一行。这是"面向接口编程"，Spring 里到处都是。

## Set

```java
Set<String> set = new HashSet<>();
set.add("a"); set.add("a");   // 第二个被忽略
set.size();                   // 1
```

用途：去重、判断"是否属于某集合"。

## Map

```java
Map<String, Integer> map = new HashMap<>();
map.put("Tom", 18);          // 存
map.get("Tom");              // 取，返回 18
map.containsKey("Tom");      // 是否有这个 key
map.put("Tom", 19);          // 同 key 覆盖

// 遍历（JS 是 for (const [k,v] of map)）
for (Map.Entry<String, Integer> e : map.entrySet()) {
    e.getKey(); e.getValue();
}
```

## ★ 前端人注意

- 集合的 `size()` 是**方法**（带括号），不是属性。`list.size()` 不是 `list.length`。
- `Map` 不能用 `[]` 取值，必须 `map.get(key)`。它不是 JS 对象。
- 遍历 `Map` 用 `entrySet()`，没有直接 `for...of`。

## 完整可运行示例

```java
--8<-- "language/ch13-collections/src/main/java/com/javaglm/language/ch13/CollectionDemo.java"
```

---

[:octicons-arrow-left-16: 上一章：枚举与包装类](12-enum-wrapper.md) ｜ 下一章：泛型
