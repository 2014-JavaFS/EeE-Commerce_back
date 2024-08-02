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


    public OrderItem findByOrderIdAndProductId(int orderId, int productId){
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId);
    }

    public boolean delete(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
        return true;
    }


    @SneakyThrows
    public OrderItem update(OrderItem orderItem) {
        OrderItem existingOrderitem = orderItemRepository.findById(orderItem.getOrderItemId()).orElseThrow(()-> new OrderItemNotFoundException("No order item found with an id of "+orderItem));
        if(orderItem.getOrder() != null) existingOrderitem.setOrder(orderItem.getOrder());

        if(orderItem.getProduct() != null) existingOrderitem.setProduct(orderItem.getProduct());

        if(orderItem.getCount() > 0) existingOrderitem.setCount(orderItem.getCount());



        return orderItemRepository.save(existingOrderitem);
    }


}
