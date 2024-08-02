package com.revature.eeecommerce.OrderItem.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTONoId {
    private Integer orderId;
    private Integer product_id;
    private Integer count;
}
