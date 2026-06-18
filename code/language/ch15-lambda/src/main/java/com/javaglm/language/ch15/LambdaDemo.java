package com.javaglm.language.ch15;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 第 15 章 · Lambda 与函数式接口。
 * Lambda 对标 JS 箭头函数：() -> 表达式。
 * 函数式接口 = 只有一个抽象方法的接口，Lambda 才能赋值给它。
 * JDK 自带一批现成函数式接口（java.util.function），不必自己写。
 */
public class LambdaDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tom", "Jerry", "Spike");

        // 1. 基础 lambda：无参
        Runnable r = () -> System.out.println("无参 lambda");
        r.run();

        // 2. 现成函数式接口（对标 TS 的 (s: string) => number 之类的函数类型）
        Function<String, Integer> length = s -> s.length();            // 入→出
        System.out.println("hello 长度 = " + length.apply("hello"));   // 5

        Predicate<String> isLong = s -> s.length() > 3;                // 入→boolean
        System.out.println("Tom 长吗? " + isLong.test("Tom"));         // false

        Consumer<String> printer = s -> System.out.println("打印：" + s);  // 只入无返回
        printer.accept("Hi");

        Supplier<String> supplier = () -> "默认值";                    // 无入有返回
        System.out.println("supply = " + supplier.get());

        // 3. 方法引用：把已有方法当函数传递，更简洁
        names.forEach(System.out::println);   // 等价 s -> System.out.println(s)

        // 4. 多行 lambda 用大括号 + return
        Function<Integer, Integer> square = x -> {
            int result = x * x;
            return result;
        };
        System.out.println("square(5) = " + square.apply(5));
    }
}
