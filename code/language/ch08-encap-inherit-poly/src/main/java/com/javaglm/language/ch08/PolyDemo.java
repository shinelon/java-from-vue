package com.javaglm.language.ch08;

/**
 * 第 08 章 · 封装、继承、多态。
 * 封装：private 字段 + getter/setter，外部不能乱改。
 * 继承：extends 单继承，super 调父类。
 * 多态：父类引用指向子类对象，调用的是子类重写后的方法。
 */
public class PolyDemo {

    public static void main(String[] args) {
        // === 封装 ===
        Account acc = new Account("Tom");
        acc.deposit(100);
        // acc.balance = -50;   // ✗ 编译报错！balance 是 private，外部不能直接改
        acc.deposit(-30);       // 被 setter 里的校验拦下，余额不变
        System.out.println(acc.getName() + " 余额 = " + acc.getBalance());  // 100

        // === 多态：父类引用指向子类对象 ===
        Animal[] zoo = {new Dog(), new Cat()};
        for (Animal a : zoo) {
            a.speak();   // 运行时根据实际类型，调用各自重写的方法
        }

        // === instanceof：判断真实类型（对标 JS 的 instanceof）===
        Animal a = new Dog();
        if (a instanceof Dog) {
            Dog d = (Dog) a;     // 向下转型（强转）
            d.bark();
        }
    }
}

// ===================== 封装 =====================
class Account {
    private String name;
    private int balance;     // private：外部不可直接访问

    Account(String name) {
        this.name = name;
    }

    // getter：只读访问
    public String getName() { return name; }
    public int getBalance() { return balance; }

    // setter 里做校验，这是"封装"的核心价值
    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("金额必须为正，忽略：" + amount);
            return;
        }
        this.balance += amount;
    }
}

// ===================== 继承 + 多态 =====================
class Animal {
    public void speak() {
        System.out.println("...");
    }
}

class Dog extends Animal {        // extends 单继承
    @Override                     // 重写标记（编译器帮你检查签名是否真的覆盖了父类方法）
    public void speak() {
        System.out.println("汪汪！");
    }

    public void bark() {
        System.out.println("Dog 专属方法");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("喵喵！");
    }
}
