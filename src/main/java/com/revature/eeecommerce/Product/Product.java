package com.revature.eeecommerce.Product;

/**
 * @author Arjun Ramsinghani
 * This is a model of the Product
 */

public class Product {
    private int product_id;
    private int price;
    private double discount;
    private String name;
    private String description;

    public Product() {}

    public Product(int product_id, int price, double discount, String name, String description) {
        this.product_id = product_id;
        this.price = price;
        this.discount = discount;
        this.name = name;
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", price=" + price +
                ", discount=" + discount +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
