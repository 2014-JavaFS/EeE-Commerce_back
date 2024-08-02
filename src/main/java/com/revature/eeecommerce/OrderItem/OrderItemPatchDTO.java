package com.revature.eeecommerce.OrderItem;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemPatchDTO {
    private Integer orderId;
    private Integer product_id;
    private Integer count;
}
