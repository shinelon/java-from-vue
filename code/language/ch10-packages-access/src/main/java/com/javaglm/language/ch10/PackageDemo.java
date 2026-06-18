package com.javaglm.language.ch10;

// import 别的包里的类（对标 ES module 的 import）
import com.javaglm.language.ch10.model.Product;

/**
 * 第 10 章 · 包与访问修饰符。
 * 包（package）就是目录，必须和目录结构一一对应——对标 ES module 的文件路径。
 * 访问修饰符控制"谁能用到这个成员"。
 */
public class PackageDemo {

    public static void main(String[] args) {
        // 同包的类，直接用
        User u = new User("Tom");
        System.out.println("public    name = " + u.name);      // public：任何地方
        System.out.println("default   tag  = " + u.tag);        // 包级私有：同包可见

        // 跨包：用 import 进来的 Product
        Product p = new Product("鼠标", 99.0);
        System.out.println("跨包 Product = " + p.getName() + " / " + p.getPrice());

        // 私有成员外部访问不到
        // System.out.println(u.id);   // ✗ id 是 private，编译报错
    }
}

/** 同包内的类。展示四种访问级别。 */
class User {
    public String name;       // public：任何包都能访问
    private int id;           // private：只有本类
    String tag;               // 不写修饰符 = 包级私有（default）：仅同包
    protected String note;    // protected：同包 + 子类

    User(String name) {
        this.name = name;
        this.id = 1;
        this.tag = "vip";
        this.note = "备注";
    }
}
