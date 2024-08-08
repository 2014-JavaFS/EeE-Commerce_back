package com.revature.eeecommerce.Cart;

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

    @Test
    public void testAddItemIntegration() throws Exception {
        String cartJSON = "{}";

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
