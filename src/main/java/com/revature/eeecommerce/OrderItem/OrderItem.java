package com.revature.eeecommerce.OrderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Order-Items")
public class OrderItem {
    @EmbeddedId
    private OrderItemId orderItemId;
    @Column(columnDefinition = "integer default 1 check (count > 0)")
    private int count;
}