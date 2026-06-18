package com.javaglm.advanced.ch36;

/**
 * 第 36 章 · 从 JDK 8 到 17 的新特性。
 * 本模块用 release 17 编译（覆盖基线的 8），演示现代语法。
 * 这些语法大多让 Java 更接近 JS/TS，对前端人友好。
 */
public class Jdk17Demo {

    public static void main(String[] args) {
        // 1. var：局部变量类型推断（JDK 10+）。对标 JS 的 let，编译期推断类型，仍是强类型。
        var name = "Tom";
        var count = 10;
        var list = new java.util.ArrayList<String>();
        list.add(name);
        System.out.println(name + ", " + count + ", list=" + list);

        // 2. 文本块（JDK 15）：多行字符串。对标 JS 反引号模板字符串。
        var json = """
                {
                  "name": "Tom",
                  "age": 18
                }
                """;
        System.out.println(json);

        // 3. record（JDK 16）：纯数据载体，一行搞定构造/getter/equals/hashCode。
        var p = new Point(3, 4);
        System.out.println("Point: " + p.x() + ", " + p.y());   // 访问器是 x() 不是 getX()

        // 4. instanceof 模式匹配（JDK 16）：强转一步到位，不用再 (String) obj。
        Object obj = "hello";
        if (obj instanceof String s) {
            System.out.println("字符串长度 = " + s.length());
        }

        // 5. switch 表达式 + 箭头（JDK 14）：能返回值、不穿透、可多值合并。
        var type = switch ("MON") {
            case "MON", "TUE", "WED", "THU", "FRI" -> "工作日";
            case "SAT", "SUN" -> "周末";
            default -> "未知";
        };
        System.out.println("类型 = " + type);

        // 6. sealed 密封类（JDK 17）：限定谁能源码继承，配合 instanceof 穷尽处理。
        System.out.println("面积 = " + area(new Circle(2)));
    }

    /** record：一行定义不可变数据类。对标 TS 的 type Point = { x: number; y: number }。 */
    record Point(int x, int y) {
    }

    /** sealed + permits：只允许 Circle / Rectangle 实现 Shape。 */
    sealed interface Shape permits Circle, Rectangle {
    }

    static final class Circle implements Shape {
        final double r;
        Circle(double r) {
            this.r = r;
        }
    }

    static final class Rectangle implements Shape {
        final double w, h;
        Rectangle(double w, double h) {
            this.w = w;
            this.h = h;
        }
    }

    /** 用 instanceof 模式匹配处理 sealed 类型（JDK 17 稳定写法）。 */
    static double area(Shape shape) {
        if (shape instanceof Circle c) {
            return Math.PI * c.r * c.r;
        } else if (shape instanceof Rectangle r) {
            return r.w * r.h;
        }
        throw new IllegalArgumentException("未知形状");
    }
}
