# 封装 · 继承 · 多态

面向对象三件套。前端人写过 JS class/extends，这里主要理解 Java 的**访问控制**和**强类型多态**。

## 封装：private + getter/setter

```java
class Account {
    private int balance;          // private：外部不能直接 balance = -50

    public int getBalance() { return balance; }       // getter
    public void deposit(int amount) {                 // setter 里做校验
        if (amount <= 0) return;
        this.balance += amount;
    }
}
```

**封装的价值**：把"如何修改 balance"的规则收进类内部，外部只能通过受控的方法访问。JS 没有真正的 `private`（`#` 私有字段是新特性），Java 的 `private` 是编译期强制的。

## 继承：extends + super

```java
class Dog extends Animal { }   // 单继承！一个类只能 extends 一个父类
```

- Java **只能单继承**（不像 Python 多继承）。要"多继承"的能力用**接口**（第 09 章）。
- `super` 调用父类的构造器/方法，对标 JS 的 `super`。

## 多态：父类引用指向子类对象

```java
Animal[] zoo = {new Dog(), new Cat()};
for (Animal a : zoo) {
    a.speak();   // Dog 打印"汪汪"，Cat 打印"喵喵"——运行时决定
}
```

这是后端最常用的模式：**方法参数声明为父类型，能接收任何子类**。Spring 里你会大量看到这种"面向接口/父类编程"。

## `@Override` 是干什么的

```java
class Dog extends Animal {
    @Override               // 告诉编译器"我要重写父类方法"
    public void speak() { }
}
```

加了 `@Override`，如果你**拼错了方法名**（比如写成 `speek`），编译器立刻报错——它发现父类没有这个方法可重写。不加的话，你以为是重写，其实是新方法，bug 很难查。**重写一律加 `@Override`。**

## instanceof 与强转

```java
Animal a = new Dog();
if (a instanceof Dog) {
    Dog d = (Dog) a;   // 向下转型（强转）后才能调用 Dog 专属方法
    d.bark();
}
```

## 完整可运行示例

```java
--8<-- "language/ch08-encap-inherit-poly/src/main/java/com/javaglm/language/ch08/PolyDemo.java"
```

## 前端人对照

| JS | Java | 备注 |
|---|---|---|
| `class Dog extends Animal` | 同 | Java **单继承** |
| `super.speak()` | 同 | 调父类 |
| 无真正私有（`#` 除外） | `private` 编译强制 | 封装更强 |
| `obj instanceof Cls` | 同 | 判断类型 |
| 无重写标记 | `@Override` | 防拼错，必加 |

---

[:octicons-arrow-left-16: 上一章：面向对象基础](07-oop-basics.md) ｜ 下一章：接口与抽象类
