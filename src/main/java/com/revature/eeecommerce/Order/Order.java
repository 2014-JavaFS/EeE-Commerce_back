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
}
