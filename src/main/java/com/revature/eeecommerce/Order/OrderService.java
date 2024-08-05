package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.util.exceptions.DataNotFoundException;
import com.revature.eeecommerce.util.interfaces.Serviceable;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService implements Serviceable<Order> {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()){
            throw new DataNotFoundException("No order information available");
        } else {
            return orders;
        }
    }

    @Override
    public Order create(Order order) {
        //TODO: validate?
        return orderRepository.save(order);
    }

    @Override
    public Order findById(int orderNumber) {
        Order foundOrder = orderRepository.findById(orderNumber)
                .orElseThrow(() -> new DataNotFoundException("No order with the ID of " + orderNumber));
        return foundOrder;
    }

    @Transactional
    @Override
    public boolean update(Order orderToUpdate) {
        //TODO: validate?
        Order foundOrder = orderRepository.findById(orderToUpdate.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("No order with the ID of " + orderToUpdate.getOrderId()));
        if (foundOrder == null){
            throw new DataNotFoundException("Order with that ID is not in the database, please check again");
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        //TODO: validate?
        orderRepository.delete(order);
        return true;
    }

    //TODO: Verify that this works
    public List<Order> findAllById(int userId) {
        List<Order> orders = orderRepository.findAllById(userId);
        if (orders.isEmpty()){
            throw new DataNotFoundException("No orders with that userId was found");
        } else {
            return orders;
        }
    }
}
