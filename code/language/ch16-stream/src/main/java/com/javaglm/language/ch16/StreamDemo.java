package com.javaglm.language.ch16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第 16 章 · Stream API。
 * Stream 对标 JS 数组的链式调用：.filter().map().reduce()。
 * 这是前端人最舒适的 API，几乎一一对应。
 * 三步：创建 stream → 中间操作（filter/map，惰性）→ 终止操作（collect/count，触发执行）。
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        // 1. filter：对标 .filter(x => x > 3)
        List<Integer> big = nums.stream()
                .filter(n -> n > 3)
                .collect(Collectors.toList());      // collect = 收集成 List
        System.out.println("大于3的 = " + big);      // [4, 5, 6]

        // 2. map：对标 .map(x => x * x)
        List<Integer> squares = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("平方 = " + squares);     // [1, 4, 9, 16, 25, 36]

        // 3. 链式组合：偶数 → ×10 → 收集
        List<Integer> chain = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 10)
                .collect(Collectors.toList());
        System.out.println("偶数×10 = " + chain);    // [20, 40, 60]

        // 4. 求和：reduce / 简便 sum
        int sum = nums.stream().mapToInt(n -> n).sum();
        System.out.println("总和 = " + sum);          // 21

        // 5. count：统计个数
        long count = nums.stream().filter(n -> n > 3).count();
        System.out.println("大于3的个数 = " + count); // 3

        // 6. findFirst + Optional：处理"可能找不到"
        Integer firstBig = nums.stream()
                .filter(n -> n > 10)
                .findFirst()          // 返回 Optional<Integer>
                .orElse(null);        // 没有就返回 null（对标 ?? 兜底）
        System.out.println("第一个>10的 = " + firstBig);  // null
    }
}
