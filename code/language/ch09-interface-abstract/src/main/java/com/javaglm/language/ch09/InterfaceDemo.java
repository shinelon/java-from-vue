package com.javaglm.language.ch09;

/**
 * 第 09 章 · 接口与抽象类。
 * 接口（interface）对标 TS 的 interface，但 Java 8 的接口还能有 default 默认实现。
 * 一个类可以实现多个接口——这就是 Java 替代"多继承"的方案。
 * 抽象类（abstract class）：不能 new，介于普通类和接口之间。
 */
public class InterfaceDemo {

    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.fly();          // 实现的抽象方法
        duck.swim();         // 接口的 default 方法（没重写就直接用）

        // 接口类型引用 = 多态
        Flyable f = new Duck();
        f.fly();

        // 抽象类：用子类实例化
        Shape circle = new Circle(2);
        System.out.println("圆面积 = " + circle.area());
    }
}

/** 接口：方法默认 public abstract（不用写也行）。 */
interface Flyable {
    void fly();
}

/** 接口的 default 方法：JDK 8 起接口可以带默认实现。 */
interface Swimmer {
    default void swim() {
        System.out.println("游泳中...");
    }
}

/** 一个类可以实现多个接口——这是 Java 做不到多继承的替代方案。 */
class Duck implements Flyable, Swimmer {
    @Override
    public void fly() {
        System.out.println("鸭子飞起来了");
    }
}

/** 抽象类：不能 new，可以有抽象方法（子类必须实现）和具体方法。 */
abstract class Shape {
    abstract double area();   // 抽象方法，没有方法体
}

class Circle extends Shape {
    private double r;
    Circle(double r) { this.r = r; }
    @Override
    double area() { return Math.PI * r * r; }
}
