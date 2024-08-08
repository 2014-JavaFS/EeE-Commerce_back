package com.revature.eeecommerce.OrderItem;

import com.revature.eeecommerce.Order.Order;
import com.revature.eeecommerce.Order.OrderRepository;
import com.revature.eeecommerce.Order.OrderService;
import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.Product.ProductService;
import com.revature.eeecommerce.User.User;
import com.revature.eeecommerce.User.UserService;
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
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderItemControllerIntegrationTestSuite {
    @MockBean
    private OrderItemRepository mockOrderItemRepository;

    @MockBean
    private OrderItemService sut;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Autowired
    private OrderItemController orderItemController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static Time now = Time.valueOf(LocalTime.now());
    private static User defaultUser = new User(1, "Amsal", "Kassam", "12345 Fake St.", "test@email.com", "Password123", User.userType.EMPLOYEE);
    private static String userJSON = "{\"userId\":1,\"firstName\":\"Amsal\",\"lastName\":\"Kassam\",\"address\":\"12345 Fake St.\",\"email\":\"test@email.com\",\"password\":\"Password123\",\"userType\":\"EMPLOYEE\"}";
    private static String productJSON = "{\"product_id\":1,\"price\":2500,\"discount\":0.20,\"name\":\"testProduct\",\"description\":\"random description\",\"quantity\":100,\"image\":\"image:url\"}";
    private static Product defaultProduct = new Product(1, 100, 0.0, "testProduct", "", 100, "image:url");
    private static Order defaultOrder = new Order(1, defaultUser, now);
    private static String orderJSON = "{\"orderId\":1,\"user\":" + userJSON + ",\"date\":\"" + now.toString() + "\"}";
    private static OrderItem defaultOrderItem = new OrderItem(1, defaultOrder, defaultProduct, 1);
    private static String orderItemJSON = "{\"orderItemId\":1,\"order\":" + orderJSON + ",\"product\":" + productJSON + "\"count\":1\"}";

//    @Test
//    public void testPostNewOrderItem() throws Exception {
//        when(sut.save()).thenReturn(defaultOrderItem);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/orderItems")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(orderItemJSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string(orderItemJSON));
//
//        verify(sut, times(1)).save(any(OrderItem.class));
//    }

    @Test
    public void testGetAllOrderItems() throws Exception {
        when(sut.findAll()).thenReturn(List.of(defaultOrderItem));

        mockMvc.perform(MockMvcRequestBuilders.get("/orderItems"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("[" + orderItemJSON + "]"));
    }

    @Test
    public void testGetOrderItem() {

    }

    @Test
    public void testGetAllByOrderId() {}

    @Test
    public void testDeleteOrderItem() {}
}
