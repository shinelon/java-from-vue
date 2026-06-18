package com.javaglm.language.ch05;

/**
 * 第 05 章 · 流程控制。
 * 好消息：Java 的 if / for / while 和 JS 几乎一模一样，前端人零成本迁移。
 * 重点差异：增强 for 循环写作 for (类型 变量 : 集合)，对应 JS 的 for...of。
 */
public class ControlFlowDemo {

    public static void main(String[] args) {
        // 1. if / else if / else —— 和 JS 完全一致
        int score = 85;
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 60) {
            System.out.println("及格");        // 命中这行
        } else {
            System.out.println("不及格");
        }

        // 2. switch —— 支持 String（JDK 8 可以）；不要忘 break
        String day = "MON";
        switch (day) {
            case "MON":
            case "TUE":
            case "WED":
            case "THU":
            case "FRI":
                System.out.println("工作日");   // 命中
                break;
            case "SAT":
            case "SUN":
                System.out.println("周末");
                break;
            default:
                System.out.println("未知");
        }

        // 3. 经典 for 循环
        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);
        }

        // 4. 增强型 for（for-each）—— 对应 JS 的 for...of，语法是冒号不是 of
        int[] nums = {10, 20, 30};
        for (int n : nums) {
            System.out.println("n = " + n);
        }

        // 5. while / do-while
        int count = 0;
        while (count < 2) {
            System.out.println("while count = " + count);
            count++;
        }

        // 6. break / continue（和 JS 一样）
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;   // 跳过 2
            }
            if (i == 4) {
                break;      // 到 4 直接结束
            }
            System.out.println("loop i = " + i);   // 打印 0, 1, 3
        }
    }
}
