package com.javaglm.language.ch07;

/**
 * 第 07 章 · 面向对象基础。
 * 对标 JS 的 class：语法高度相似，但 Java 更严格——字段必须声明类型、构造器名=类名。
 * 一个 .java 文件只能有一个 public class，且文件名必须与它同名。
 */
public class OopBasicsDemo {

    public static void main(String[] args) {
        // new 创建对象，对标 JS 的 new User("Tom", 18)
        User u1 = new User("Tom", 18);
        User u2 = new User("Jerry");     // 用第二个构造器

        u1.introduce();   // 我是 Tom，今年 18 岁
        u2.introduce();   // 我是 Jerry，今年 0 岁

        // 访问字段 / 方法（. 语法，和 JS 一样）
        System.out.println("u1 的名字 = " + u1.name);
    }
}

/** 一个简单的类：字段 + 构造器 + 方法。 */
class User {

    // 字段：必须声明类型（对标 JS class 里的字段，但 Java 不写类型会报错）
    String name;
    int age;

    // 构造器：方法名就是类名，没有返回类型。对标 JS 的 constructor(name, age)
    User(String name, int age) {
        // this 指向当前对象实例，区分参数 name 和字段 name
        this.name = name;
        this.age = age;
    }

    // 构造器重载：只传名字时，年龄默认 0
    User(String name) {
        this.name = name;   // age 默认为 int 的默认值 0
    }

    // 实例方法（不带 static）：必须通过对象调用
    void introduce() {
        System.out.println("我是 " + name + "，今年 " + age + " 岁");
    }
}
