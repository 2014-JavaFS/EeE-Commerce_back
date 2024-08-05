package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.Cart.Cart;
import com.revature.eeecommerce.Cart.CartService;
import com.revature.eeecommerce.OrderItem.OrderItem;
import com.revature.eeecommerce.OrderItem.OrderItemService;
import com.revature.eeecommerce.util.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public @ResponseBody List<Order> getAllOrders(){ return orderService.findAll(); }

    @PostMapping
    public ResponseEntity<Order> checkout(@Valid @RequestBody Order order, @RequestHeader String userType, @RequestHeader int userId){
        //TODO: check this if statement for correct logic
        if (!userType.equals("CUSTOMER")) throw new UnauthorizedException("You are not logged in as a customer!");
        List<Cart> totalCart = cartService.findCartByUserId(userId);
        Order newOrder = orderService.create(order);
        totalCart.forEach((cartItem) -> {
            OrderItem newOrderItem = new OrderItem(cartItem, newOrder);
            orderItemService.create(newOrderItem);
            cartService.deleteCart(cartItem.getCartId());
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> putUpdateOrder(@Valid @RequestBody Order updatedOrder) {
        return ResponseEntity.ok(orderService.update(updatedOrder));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@PathVariable int userId){
        return ResponseEntity.ok(orderService.findAllById(userId));
    }
}
