package com.revature.eeecommerce.Cart;

public class Cart {
    private int userId;
    private int productId;
    private int count;

    public Cart(){}

    public Cart(int userId, int productId, int count) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}
