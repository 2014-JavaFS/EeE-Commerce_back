package com.revature.eeecommerce.OrderItem;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    private int orderId;
    private int productId;
}