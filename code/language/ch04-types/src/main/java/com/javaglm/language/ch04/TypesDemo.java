package com.javaglm.language.ch04;

/**
 * 第 04 章 · 基本类型与变量 —— 可运行示例。
 *
 * <p>本文件被 docs/02-language/04-types-variables.md 通过 mkdocs snippet 直接引用：
 * 文档页面展示的内容 == 这里编译的那份，改一处自动同步、零漂移。
 *
 * <p>运行方式：{@code mvn exec:java}（本模块）或 IDEA 里点运行按钮。
 */
public class TypesDemo {

    public static void main(String[] args) {
        // 1. 基本类型：类型必须写在变量名前面（对比 JS 的 let count = 10）
        int count = 10;
        long bigNumber = 10000000000L;   // long 字面量要加后缀 L
        double price = 19.99;
        boolean isOpen = true;
        char first = 'T';                 // 单引号 = 单个字符；双引号才是字符串

        System.out.println("count      = " + count);
        System.out.println("bigNumber  = " + bigNumber);
        System.out.println("price      = " + price);
        System.out.println("isOpen     = " + isOpen);
        System.out.println("first      = " + first);

        // 2. 引用类型：String（双引号）
        String name = "Tom";
        System.out.println("name       = " + name);

        // 3. ★ 前端人最大的坑：字符串比较内容必须用 .equals()，绝不能用 ==
        String a = new String("hi");
        String b = new String("hi");
        System.out.println("a == b           ? " + (a == b));          // false（比较的是地址）
        System.out.println("a.equals(b)      ? " + a.equals(b));       // true （比较的是内容）

        // 4. 类型一旦声明就锁死：取消下面这行注释会导致编译报错（incompatible types）
        // count = "十";   // ✗ int 永远不能装字符串
    }
}
