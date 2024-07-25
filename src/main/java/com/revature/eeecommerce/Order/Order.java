package com.revature.eeecommerce.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderId;
    private int userId;
    private Timestamp date;
}
