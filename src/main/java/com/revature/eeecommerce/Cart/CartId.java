package com.revature.eeecommerce.Cart;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartId implements Serializable {

    private int userId;
    private int productId;

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

    @Override
    public String toString() {
        return "CartId{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
