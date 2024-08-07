package com.revature.eeecommerce.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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


}
