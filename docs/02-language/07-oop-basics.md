# 面向对象基础

如果你写过 ES6 的 `class`，Java 的类你会觉得很亲切——**语法高度相似**。
差异主要在"更严格"：字段必须声明类型、构造器名等于类名、一个文件一个 public 类。

## 定义一个类

```java
public class User {
    String name;      // 字段：必须写类型
    int age;

    // 构造器：名字 = 类名（不是 constructor 关键字）
    User(String name, int age) {
        this.name = name;   // this 指向当前实例
        this.age = age;
    }

    void introduce() {      // 实例方法
        System.out.println("我是 " + name);
    }
}
```

## 创建对象

```java
User u = new User("Tom", 18);   // 对标 JS 的 new User("Tom", 18)
u.introduce();
u.name;   // 访问字段，点语法
```

## `this` 的差别

- **JS**：`this` "飘"——谁调用指向谁，箭头函数又另说，是面试重灾区。
- **Java**：`this` **稳定**指向当前对象实例，不会"飘"。行为可预测。

## 三条铁律

!!! warning "Java 类的硬规矩"
    1. **一个 `.java` 文件只能有一个 `public` 类**，且文件名必须和它同名。
    2. **字段必须声明类型**：`String name;` 不能写成 `name;`。
    3. **构造器名 = 类名**，没有 `constructor` 关键字，也没有返回类型。

## 完整可运行示例

```java
--8<-- "language/ch07-oop-basics/src/main/java/com/javaglm/language/ch07/OopBasicsDemo.java"
```

!!! note "为什么 demo 里有 User 但没 public"
    `User` 类没有 `public` 修饰符（包级可见），这样它和 `OopBasicsDemo` 能共存于同一文件。
    实际项目里一个类一个文件更规范，教程为了集中展示才放一起。

---

[:octicons-arrow-left-16: 上一章：方法与数组](06-methods-arrays.md) ｜ 下一章：封装·继承·多态
