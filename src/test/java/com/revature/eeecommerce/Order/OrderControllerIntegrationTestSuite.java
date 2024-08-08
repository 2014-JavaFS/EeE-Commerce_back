package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;

import static com.revature.eeecommerce.User.User.userType.CUSTOMER;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTestSuite {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    @Autowired
    private OrderController orderController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCheckout() {
        Order validOrder = new Order(1, new User(4, "Donkey", "Kong", "One Hacker Way", "dkong@mariobros.com", "Pass435", CUSTOMER), Time.valueOf("14:36:28"));

    }
}
