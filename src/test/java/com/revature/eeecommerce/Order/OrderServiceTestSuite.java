package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.OffsetDateTime;

import static com.revature.eeecommerce.User.User.userType.CUSTOMER;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTestSuite {
    @Mock // indicates Object needing to be mocked
    private OrderRepository mockOrderRepository;

    @InjectMocks // injects our ProductRepository into our ProductService as a mocked object
    private OrderService sut;

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAllById() {
    }
}
