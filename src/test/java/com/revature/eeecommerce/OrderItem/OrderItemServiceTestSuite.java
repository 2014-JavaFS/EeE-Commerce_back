package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.Order.Order;
import com.revature.eeecommerce.User.User;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.revature.eeecommerce.User.User.userType.CUSTOMER;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceTestSuite {
    @Mock
    private OrderItemRepository mockOrderItemRepository;

    @InjectMocks
    private OrderItemService sut;
    private OrderItem orderItem;

//    @Test
//    public void testCreateOrderItem() {
//        orderItem = new OrderItem();
//        OrderItem validOrderItem = new OrderItem(1, new Order(1, new User(2, "King", "Kong", "Empire State Building", "daking@skull.com", "Password123", CUSTOMER), ), 1);
//    }
}
