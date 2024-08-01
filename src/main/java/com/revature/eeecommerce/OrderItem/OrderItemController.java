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
        works with request body(if database has an Order row w/order_id = 1 and Product row w/product_id = 1:
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

    /**
     * Takes in an OrderItem as a json and returns a response with an OrderItemDTO object as the json response body.
     * Requires there to be an entry in the Order table and an entry in the Product table with the same order_id and product_id as in the request body.
     * @param orderItem - OrderItem json object with an existing order_id and product_id
     * @return orderItemDTO - OrderItemDTO with only orderItemId, orderId, product_id and count instead of Order and Product objects in place of the id's
     */
    @PostMapping
    private ResponseEntity<OrderItemDTO> postNewOrderItem(@Valid @RequestBody OrderItem orderItem){
        OrderItem newOrderItem = orderItemService.create(orderItem);
        OrderItemDTO orderItemDTO = mapToDTO(newOrderItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemDTO);
    }

    /**
     * Takes in an OrderItem object and returns an OrderItemDTO to simplify the response body with only relevant data.
     * @param orderItem - OrderItem object with Order and Product objects
     * @return - OrderItemDTO object containing orderItemId, orderId, product_id, and count
     */
    private OrderItemDTO mapToDTO(OrderItem orderItem){
        return new OrderItemDTO(
                orderItem.getOrderItemId(),
                orderItem.getOrder().getOrderId(),
                orderItem.getProduct().getProduct_id(),
                orderItem.getCount()
        );
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
