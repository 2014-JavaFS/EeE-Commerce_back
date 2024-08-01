package com.revature.eeecommerce.OrderItem;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private int orderItemId;
    private int orderId;
    private int product_id;
    private int count;
}
