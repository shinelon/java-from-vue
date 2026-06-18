package com.javaglm.language.ch12;

/**
 * 第 12 章 · 枚举与包装类。
 * 枚举（enum）：类型安全的命名常量，本质是个特殊类，可带字段/方法。
 * 包装类（Integer/Double...）：把基本类型包成对象，提供转换工具方法。
 */
public class EnumWrapperDemo {

    public static void main(String[] args) {
        // 1. 枚举：取值固定、类型安全
        Status s = Status.RUNNING;
        System.out.println(s);                 // RUNNING
        System.out.println(s.getLabel());      // 运行中

        // 枚举很适合 switch
        switch (s) {
            case PENDING: System.out.println("等待中"); break;
            case RUNNING: System.out.println("运行中"); break;   // 命中
            case DONE:    System.out.println("已完成"); break;
        }

        // 2. 包装类：基本类型 ↔ 对象
        Integer boxed = 20;          // 自动装箱：int → Integer
        int unboxed = boxed;         // 自动拆箱：Integer → int

        // 3. 包装类的实用方法（字符串转数字，前端常用场景）
        int parsed = Integer.parseInt("123");
        double d = Double.parseDouble("3.14");
        System.out.println("parsed = " + parsed + ", d = " + d);

        // 4. ★ 前端人坑：Integer 用 == 比较可能出错（有缓存范围 -128~127）
        Integer a = 127, b = 127;
        Integer c = 200, e = 200;
        System.out.println("127 == 127 ? " + (a == b));          // true（命中缓存）
        System.out.println("200 == 200 ? " + (c == e));          // false（超缓存，坑！）
        System.out.println("200 equals 200 ? " + c.equals(e));   // true ✓
    }
}

/** 枚举：本质是类，可以有字段、构造器、方法。 */
enum Status {
    PENDING("等待中"),
    RUNNING("运行中"),
    DONE("已完成");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
