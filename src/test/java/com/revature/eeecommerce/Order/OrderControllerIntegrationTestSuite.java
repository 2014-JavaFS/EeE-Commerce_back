package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void testCheckout() throws Exception {
        String orderJSON = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("orders")
                .content(orderJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
