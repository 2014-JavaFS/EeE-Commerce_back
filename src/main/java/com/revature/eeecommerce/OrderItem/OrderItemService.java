package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.util.exceptions.OrderItemNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemService{
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository){ this.orderItemRepository = orderItemRepository; }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem create(OrderItem newOrderItem) {
        return orderItemRepository.save(newOrderItem);
    }

    @SneakyThrows
    public OrderItem findById(int orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderItemNotFoundException("Nothing in the database with ID of " + orderItemId));
    }

//    @SneakyThrows
//    public OrderItem findByOrderOrderIdAndProductProductId(int orderId, int productId){
//        return orderItemRepository.findByOrderOrderIdAndProductProductId(orderId, productId).orElseThrow(() -> new OrderItemNotFoundException("No order item found with that order id and product id."));
//    }

    public boolean delete(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
        return true;
    }


    public boolean update(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
        return true;
    }


}
