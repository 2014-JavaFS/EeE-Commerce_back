package com.revature.eeecommerce.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTestSuite {
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductService productService;
    @Autowired
    private ProductController productController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProducts() throws Exception {
//        List<Product> products = Arrays.asList(
//                new Product(2, 2400, 0.2, "E Shirt", "Let everyone know you love the letter E", 200, "https://th.bing.com/th/id/OIG2.L4QTYfR6oNyS5Jq.QasC?w=270&h=270&c=6&r=0&o=5&pid=ImgGn")
//        );
//        when(productRepository.findAll()).thenReturn(products);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].product_id").value(products.get(0).getProduct_id()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(products.get(0).getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(products.get(0).getPrice()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].product_id").value(products.get(1).getProduct_id()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(products.get(1).getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(products.get(1).getPrice()));
//
//        verify(productRepository, times(1)).findAll();
    }
}
