package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.Order.Order;
import com.revature.eeecommerce.OrderItem.dtos.OrderItemDTO;
import com.revature.eeecommerce.OrderItem.dtos.OrderItemPatchDTO;
import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.util.exceptions.UnauthorizedException;
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
    private ResponseEntity<OrderItemDTO> postNewOrderItem(@Valid @RequestBody OrderItem orderItem, @RequestHeader String userType) throws UnauthorizedException {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }
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
    private OrderItemDTO mapToDTO(OrderItem orderItem) {
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

    @GetMapping("/{orderId}/{productId}")
    private ResponseEntity<OrderItem> findByOrderOrderIdAndProductProductId(@PathVariable int orderId, @PathVariable int productId){
        return ResponseEntity.ok(orderItemService.findByOrderIdAndProductId(orderId, productId));
    }

    @DeleteMapping("/{orderItemId}")
    private ResponseEntity<Void> deleteOrderItem(@PathVariable int orderItemId, @RequestHeader String userType) throws UnauthorizedException {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }

        orderItemService.delete(orderItemId);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{orderItemId}")
//    private ResponseEntity<OrderItemDTO> putUpdateOrderItem(@Valid @RequestBody OrderItemDTO orderItemDTO, @PathVariable int orderItemId, @RequestHeader String userType) throws UnauthorizedException {
//        if (!userType.equals("EMPLOYEE")) {
//            throw new UnauthorizedException("You are not logged in as a Seller");
//        }
//
//        OrderItem existingOrderItem = orderItemService.findById(orderItemId);
//
//        Order order = new Order();
//        order.setOrderId(orderItemId);
//        existingOrderItem.setOrder(order);
//
//        Product product = new Product();
//        product.setProduct_id(orderItemDTO.getProduct_id());
//        existingOrderItem.setProduct(product);
//
//        existingOrderItem.setCount(orderItemDTO.getCount());
//
//        OrderItem updatedOrderItem = orderItemService.update(existingOrderItem);
//
//        return ResponseEntity.ok(mapToDTO(updatedOrderItem));
//    }

    @PatchMapping("/{orderItemId}")
    private ResponseEntity<OrderItemDTO> patchUpdateOrderItem(@Valid @RequestBody OrderItemPatchDTO patchDTO, @PathVariable int orderItemId, @RequestHeader String userType) throws UnauthorizedException {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }

        OrderItem existingOrderItem = orderItemService.findById(orderItemId);

        if(patchDTO.getOrderId() != null) {
            Order order = new Order();
            order.setOrderId(patchDTO.getOrderId());
            existingOrderItem.setOrder(order);
        }

        if(patchDTO.getProduct_id() != null) {
            Product product = new Product();
            product.setProduct_id(patchDTO.getProduct_id());
            existingOrderItem.setProduct(product);
        }

        if(patchDTO.getCount() != null) {
            existingOrderItem.setCount(patchDTO.getCount());
        }

        OrderItem updatedOrderItem = orderItemService.update(existingOrderItem);

        OrderItemDTO orderItemDTO = mapToPatchDTO(updatedOrderItem);

        return ResponseEntity.ok(orderItemDTO);
    }

    private OrderItemDTO mapToPatchDTO(OrderItem originalOrderItem){
        return new OrderItemDTO(
                originalOrderItem.getOrderItemId(),
                originalOrderItem.getOrder().getOrderId(),
                originalOrderItem.getProduct().getProduct_id(),
                originalOrderItem.getCount()
        );
    }
}
