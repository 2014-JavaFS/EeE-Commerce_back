package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.util.exceptions.OrderItemNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemService{
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository){ this.orderItemRepository = orderItemRepository; }


    public OrderItem create(OrderItem newOrderItem) {
        return orderItemRepository.save(newOrderItem);
    }

    @SneakyThrows
    public OrderItem findById(int orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderItemNotFoundException("Nothing in the database with ID of " + orderItemId));
    }


    public boolean delete(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
        return true;
    }


    public boolean update(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
        return true;
    }
}
