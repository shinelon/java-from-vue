package com.javaglm.language.ch13;

import java.util.*;

/**
 * 第 13 章 · 集合框架。
 * 最重要的三个：List（有序可重复）、Set（不重复）、Map（键值对）。
 * 对标 JS：List≈Array、Set≈Set、Map≈Map。注意 Java 用接口 + 实现类两层。
 */
public class CollectionDemo {

    public static void main(String[] args) {
        // === List：有序、可重复 —— 对标 JS Array ===
        List<String> list = new ArrayList<>();   // 接口 List，实现 ArrayList（动态数组）
        list.add("Tom");
        list.add("Jerry");
        list.add("Tom");                // 允许重复
        System.out.println(list);       // [Tom, Jerry, Tom]
        System.out.println("get(0) = " + list.get(0));
        list.remove("Tom");             // 删第一个匹配
        System.out.println("size = " + list.size());

        // === Set：不重复 —— 对标 JS Set ===
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("a");                   // 重复被忽略
        System.out.println("set = " + set + " size = " + set.size());

        // === Map：键值对 —— 对标 JS Map / 对象 ===
        Map<String, Integer> map = new HashMap<>();
        map.put("Tom", 18);
        map.put("Jerry", 20);
        map.put("Tom", 19);             // 同 key 覆盖
        System.out.println("Tom 年龄 = " + map.get("Tom"));   // 19
        System.out.println("包含 Jerry? " + map.containsKey("Jerry"));

        // 遍历 Map：用 entrySet（JS 里是 map.entries()）
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
}
