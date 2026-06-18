package com.javaglm.language.ch14;

import java.util.ArrayList;
import java.util.List;

/**
 * 第 14 章 · 泛型。
 * 对标 TS 的泛型：<T> 是类型参数，用的时候才确定具体类型。
 * 好处：类型安全 + 免去强转。Spring / MyBatis-Plus 大量使用泛型。
 */
public class GenericDemo {

    public static void main(String[] args) {
        // 1. 泛型集合：限定元素类型，对标 TS 的 string[]
        List<String> names = new ArrayList<>();   // 右边的 <> 叫"钻石操作符"，可省略类型
        names.add("Tom");
        // names.add(123);   // ✗ 编译报错，只能放 String

        String first = names.get(0);   // 拿出来直接是 String，不用强转
        System.out.println("first = " + first);

        // 2. 自定义泛型类：对标 TS 的 class Box<T>
        Box<Integer> intBox = new Box<>(99);
        Box<String> strBox = new Box<>("hello");
        System.out.println("intBox = " + intBox.get() + ", strBox = " + strBox.get());

        // 3. 泛型方法：<T> 声明类型参数，对标 TS 的 function firstOf<T>(arr: T[]): T
        System.out.println("firstOf = " + firstOf(names));
    }

    /** 泛型方法：T 是类型参数，在调用时由实参推断。 */
    static <T> T firstOf(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

/** 泛型类：Box<T>，T 在实例化时才确定类型。 */
class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
