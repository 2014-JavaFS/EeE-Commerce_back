package com.revature.eeecommerce.Order;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private Timestamp date;

    public Order(int orderId, int userId, Timestamp date) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
