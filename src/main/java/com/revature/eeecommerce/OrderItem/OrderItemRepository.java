package com.revature.eeecommerce.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
//    Optional<OrderItem> findByOrderOrderIdAndProductProductId(int orderId, int productId);
}
