package com.javaglm.language.ch10.model;

/** 放在子包 model 里，演示跨包访问。 */
public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
