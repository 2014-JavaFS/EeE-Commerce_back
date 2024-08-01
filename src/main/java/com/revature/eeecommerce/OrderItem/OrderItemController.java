package com.revature.eeecommerce.OrderItem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;

    }


    /*
        works with request body(if database has an order entry w/order_id = 1 an product entry w/product_id = 1:
    {
        "order": {
          "orderId": 1
        },
        "product": {
          "product_id": 1
        },
        "count": 3
    }
     */
    @PostMapping
    private ResponseEntity<OrderItem> postNewOrderItem(@Valid @RequestBody OrderItem orderItem){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.create(orderItem));
    }

    @GetMapping
    private ResponseEntity<List<OrderItem>> findAll(){
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/{orderItemId}")
    private ResponseEntity<OrderItem> getOrderItem(@PathVariable int orderItemId){
        return ResponseEntity.ok(orderItemService.findById(orderItemId));
    }

    // TODO: set up method
//    @GetMapping("/{orderId}/{productId}")
//    private ResponseEntity<OrderItem> findByOrderOrderIdAndProductProductId(@PathVariable int orderId, @PathVariable int productId){
//        return ResponseEntity.ok(orderItemService.findByOrderOrderIdAndProductProductId(orderId, productId));
//    }

    @DeleteMapping("/{orderItemId}")
    private ResponseEntity<Void> deleteOrderItem(@PathVariable int orderItemId){
        orderItemService.delete(orderItemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    private ResponseEntity<OrderItem> putUpdateOrderItem(@Valid @RequestBody OrderItem orderItem){
        orderItemService.update(orderItem);
        return ResponseEntity.ok(orderItem);
    }
}
