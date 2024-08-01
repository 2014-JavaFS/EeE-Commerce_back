package com.revature.eeecommerce.Product;

import com.revature.eeecommerce.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** SERVICE CLASS DOCUMENTATION
 * @author Arjun Ramsinghani
 * The Service class is used to define the business logic coming to the backend by use of Spring Boot and will communicate with the database through Spring Data.
 * Instructions on how each method should interact are within each method call.
 */

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // remove products by an id (BIG MAYBE)

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return null;
        }

        else {
            return products;
        }
    }

    public Product findById(int product_id) {
        Product foundProduct = productRepository.findById(product_id).orElseThrow(() -> new DataNotFoundException("No product with the ID: " + product_id));
        return foundProduct;
    }

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Transactional
    public boolean updateProduct(Product updateProduct) {
        Product foundProduct = productRepository.findById(updateProduct.getProduct_id()).orElseThrow(() -> new DataNotFoundException("No product with the ID: " + updateProduct.getProduct_id()));

        if (foundProduct == null) {
            return false;
        }

        productRepository.saveAndFlush(updateProduct);
        return true;
    }

    public boolean deleteProduct(Product removeProduct) {
        Product foundProduct = productRepository.findById(removeProduct.getProduct_id()).orElseThrow(() -> new DataNotFoundException("No product with the ID: " + removeProduct.getProduct_id()));

        if (foundProduct == null) {
            return false;
        }

        productRepository.delete(foundProduct);
        return true;
    }
}
