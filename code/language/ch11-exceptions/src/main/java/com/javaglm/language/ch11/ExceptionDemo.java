package com.javaglm.language.ch11;

/**
 * 第 09 章异常处理。
 * ★ JS 没有的概念：受检异常（checked exception）。
 *   继承 Exception 的异常，编译器强制你要么 try/catch 要么 throws，否则编译不通过。
 *   继承 RuntimeException 的叫非受检异常，和 JS 一样可以不处理。
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        // 1. try / catch / finally：和 JS 几乎一样
        try {
            int[] arr = {1, 2};
            System.out.println(arr[5]);        // 越界，运行时异常
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获越界：" + e.getMessage());
        } finally {
            System.out.println("finally 无论如何都执行（对标 JS finally）");
        }

        // 2. 受检异常：编译器强制处理。不 try/catch 这行根本编译不过
        try {
            readFile();
        } catch (BizException e) {
            System.out.println("处理受检异常：" + e.getMessage());
        }

        // 3. JDK 7+ 的 multi-catch：一个 catch 抓多种异常
        try {
            int n = Integer.parseInt("abc");
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("数字格式或空指针：" + e.getMessage());
        }

        // 4. 主动抛出（对标 JS throw）
        try {
            setAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("参数非法：" + e.getMessage());
        }
    }

    /** throws 声明：告诉调用方"我可能抛这个受检异常，你必须处理"。 */
    static void readFile() throws BizException {
        throw new BizException("业务异常：文件不存在");
    }

    static void setAge(int age) {
        if (age < 0) {
            // RuntimeException 是非受检异常，不用 throws 声明
            throw new IllegalArgumentException("年龄不能为负：" + age);
        }
    }
}

/** 自定义受检异常：继承 Exception。 */
class BizException extends Exception {
    BizException(String msg) {
        super(msg);
    }
}
