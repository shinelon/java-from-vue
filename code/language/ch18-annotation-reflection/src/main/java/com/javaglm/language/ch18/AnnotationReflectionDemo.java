package com.javaglm.language.ch18;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 第 18 章 · 注解与反射基础 —— Spring 的灵魂。
 * 注解（@）：给代码贴"标签"，本身不做事，靠"别人"去读它。
 * 反射：运行时动态拿到类的字段/方法/注解信息。
 * Spring 的 @RestController / @Autowired 底层就是：用反射扫描注解，然后干活。
 */
public class AnnotationReflectionDemo {

    public static void main(String[] args) {
        // 1. 反射：运行时拿到类的结构信息
        Class<?> clazz = User.class;
        System.out.println("类名 = " + clazz.getSimpleName());
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("  字段 = " + f.getName() + " : " + f.getType().getSimpleName());
        }

        // 2. 读取类上的自定义注解（这是 Spring 框架的核心套路）
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            System.out.println("User 映射的表名 = " + table.name());
        }
    }
}

/** 自定义注解：标记实体对应的数据库表名。 */
@Retention(RetentionPolicy.RUNTIME)   // 必须 RUNTIME，反射才能读到
@Target(ElementType.TYPE)             // 只能贴在类上
@interface Table {
    String name();                    // 注解的"属性"，用法像方法
}

/** 用注解标记：这个类对应数据库表 t_user。 */
@Table(name = "t_user")
class User {
    private Long id;
    private String username;
}
