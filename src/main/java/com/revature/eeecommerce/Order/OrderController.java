package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public @ResponseBody List<Order> getAllOrders(){ return orderService.findAll(); }

    @PostMapping
    public ResponseEntity<Order> postNewOrder(@RequestBody Order order, @RequestHeader String memberType){
        //TODO: check this if statement for correct logic
        if (!memberType.equals("CUSTOMER")) throw new UnauthorizedException("You are not logged in as a customer!");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> putUpdateOrder(@RequestBody Order updatedOrder) {
        return ResponseEntity.ok(orderService.update(updatedOrder));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@RequestBody int userId){
        return ResponseEntity.ok(orderService.findAllById(userId));
    }
}
