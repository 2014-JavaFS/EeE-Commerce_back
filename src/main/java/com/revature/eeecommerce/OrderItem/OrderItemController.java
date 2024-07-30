package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.Order.OrderService;
import com.revature.eeecommerce.Product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private final OrderItemService orderItemService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, ProductService productService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping
    private ResponseEntity<OrderItem> postNewOrderItem(@Valid @RequestBody OrderItem orderItem){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.create(orderItem));
    }

    @GetMapping("/{orderItemId}")
    private ResponseEntity<OrderItem> getOrderItemById(@PathVariable int orderItemId){
        return ResponseEntity.ok(orderItemService.findById(orderItemId));
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteOrderItem(@Valid @RequestBody OrderItem orderItem){
        orderItemService.delete(orderItem);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    private ResponseEntity<Void> putUpdateOrderItem(@Valid @RequestBody OrderItem orderItem){
        orderItemService.update(orderItem);
        return ResponseEntity.noContent().build();
    }
}
