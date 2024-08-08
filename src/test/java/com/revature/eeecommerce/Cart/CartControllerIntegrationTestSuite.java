package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.Product.Product;
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

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIntegrationTestSuite {
    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CartService cartService;

    @Autowired
    private CartController cartController;

    @Autowired
    private MockMvc mockMvc;

    private static Cart defaultCart = new Cart(1,
            new User(1, "Donkey", "Kong", "12345 Fake St.", "dkong@mail.com", "Password@123", User.userType.CUSTOMER),
            new Product(2, 2400, 0.2, "E Shirt", "Let everyone know you love the letter E", 200, "https://th.bing.com/th/id/OIG2.L4QTYfR6oNyS5Jq.QasC?w=270&h=270&c=6&r=0&o=5&pid=ImgGn"),
            1);

    private static String cartJSON = "{\"cartId\":1, \"userId\":1,\"firstName\":\"Amsal\",\"lastName\":\"Kassam\",\"address\":\"12345 Fake St.\",\"email\":\"test@email.com\",\"password\":\"Password123\",\"userType\":\"EMPLOYEE\"}";

    @Test
    public void testAddItemIntegration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                .content(cartJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void testDeleteIntegration() throws Exception {
        String cartJSON = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .content(cartJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
