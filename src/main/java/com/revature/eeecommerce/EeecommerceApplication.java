package com.revature.eeecommerce;

import com.revature.eeecommerce.Product.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EeecommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductController.class, args);
	}

}
