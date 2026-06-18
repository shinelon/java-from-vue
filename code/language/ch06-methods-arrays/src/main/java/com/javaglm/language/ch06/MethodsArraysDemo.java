package com.javaglm.language.ch06;

import java.util.Arrays;

/**
 * 第 06 章 · 方法与数组。
 * 方法：必须有返回类型、必须挂在类里、参数要写类型。
 * 数组：长度固定（创建后不可变），要"动态长度"请用 List（第 13 章）。
 */
public class MethodsArraysDemo {

    public static void main(String[] args) {
        // 1. 调用方法
        int s = add(2, 3);
        System.out.println("add(2,3) = " + s);

        // 2. 方法重载（同名、参数不同）—— JS 没有这个特性
        System.out.println("add(1,2,3)   = " + add(1, 2, 3));
        System.out.println("add(1.5,2.5) = " + add(1.5, 2.5));

        // 3. 可变参数 varargs（本质是数组）
        System.out.println("sum = " + sum(1, 2, 3, 4, 5));

        // 4. 数组：声明 + 创建 + 访问
        int[] arr = new int[3];        // 长度为 3，元素默认 0
        arr[0] = 10;
        arr[1] = 20;
        System.out.println("arr = " + Arrays.toString(arr));   // [10, 20, 0]
        System.out.println("arr.length = " + arr.length);     // 3

        // 5. 数组创建时直接赋值
        String[] names = {"Tom", "Jerry", "Spike"};
        for (String name : names) {
            System.out.println("name = " + name);
        }

        // 6. 数组是定长的：下面这行会运行时抛异常
        // arr[5] = 99;  // ArrayIndexOutOfBoundsException

        // 7. 排序
        int[] nums = {5, 2, 8, 1};
        Arrays.sort(nums);
        System.out.println("sorted = " + Arrays.toString(nums));  // [1, 2, 5, 8]
    }

    /** 两个 int 相加。返回类型写在方法名前面。 */
    static int add(int a, int b) {
        return a + b;
    }

    /** 重载：三个 int。JS 做不到真正的重载，只能靠 arguments/默认值模拟。 */
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    /** 重载：double 版本。 */
    static double add(double a, double b) {
        return a + b;
    }

    /** 可变参数：int... 等价于 int[]。 */
    static int sum(int... values) {
        int total = 0;
        for (int v : values) {
            total += v;
        }
        return total;
    }
}
