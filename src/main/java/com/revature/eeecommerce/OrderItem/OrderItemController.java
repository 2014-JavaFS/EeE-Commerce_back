package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.Order.Order;
import com.revature.eeecommerce.OrderItem.dtos.OrderItemDTO;
import com.revature.eeecommerce.OrderItem.dtos.OrderItemDTONoId;
import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.util.exceptions.OrderItemNotFoundException;
import com.revature.eeecommerce.util.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;

    }

    /**
     * Takes in an OrderItem as a json and returns a response with an OrderItemDTO object as the json response body.
     * Requires there to be an entry in the Order table and an entry in the Product table with the same order_id and product_id as in the request body.
     * @param postDTO - OrderItemDTO json object with an existing order_id and product_id
     * @return orderItemDTO - OrderItemDTO with only orderItemId, orderId, product_id and count instead of Order and Product objects in place of the id's
     */
    @PostMapping
    private ResponseEntity<OrderItemDTO> postNewOrderItem(@Valid @RequestBody OrderItemDTONoId postDTO, @RequestHeader String userType) throws UnauthorizedException {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }

        Order order = new Order();
        order.setOrderId(postDTO.getOrderId());

        Product product = new Product();
        product.setProduct_id(postDTO.getProduct_id());

        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setOrder(order);
        newOrderItem.setProduct(product);
        newOrderItem.setCount(postDTO.getCount());


        newOrderItem = orderItemService.create(newOrderItem);
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
    private ResponseEntity<List<OrderItemDTO>> findAll(){
        List<OrderItem> orderItems = orderItemService.findAll();
        List<OrderItemDTO> orderItemDTOs = orderItems.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderItemDTOs);
    }

    @GetMapping("/{orderItemId}")
    private ResponseEntity<?> getOrderItem(@PathVariable int orderItemId){
        try {
            OrderItem orderItem = orderItemService.findById(orderItemId);
            OrderItemDTO orderItemDTO = mapToDTO(orderItem);
            return ResponseEntity.ok(orderItemDTO);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}/{productId}")
    private ResponseEntity<?> findByOrderOrderIdAndProductProductId(@PathVariable int orderId, @PathVariable int productId){
        try{
            OrderItem orderItem = orderItemService.findByOrderIdAndProductId(orderId, productId);
            OrderItemDTO orderItemDTO = mapToDTO(orderItem);
            return ResponseEntity.ok(orderItemDTO);
        } catch(OrderItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
    private ResponseEntity<?> patchUpdateOrderItem(@Valid @RequestBody OrderItemDTONoId patchDTO, @PathVariable int orderItemId, @RequestHeader String userType) throws UnauthorizedException {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }

        try {
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

            OrderItemDTO orderItemDTO = mapToDTO(updatedOrderItem);

            return ResponseEntity.ok(orderItemDTO);
        } catch(OrderItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
