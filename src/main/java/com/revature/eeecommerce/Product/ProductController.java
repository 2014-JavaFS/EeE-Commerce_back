package com.revature.eeecommerce.Product;

import com.revature.eeecommerce.util.exceptions.DataNotFoundException;
import com.revature.eeecommerce.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** CONTROLLER CLASS DOCUMENTATION
 * @author Arjun Ramsinghani
 * The Controller class will interact with the front end part of our application and send the data coming through there through our Service class for processing and validation.
 * Instructions on how each method should interact are within each method call.
 */

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // remove products

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            throw new DataNotFoundException("No products found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> postNewProduct(@RequestBody Product newProduct, @RequestHeader String userType) {
        if (!userType.equals("EMPLOYEE")) {
            throw new UnauthorizedException("You are not logged in as a Seller");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(newProduct));
    }

    @PatchMapping
    public ResponseEntity<Boolean> patchUpdateProduct(@RequestBody Product updatedProduct) {
        boolean updateProduct = productService.updateProduct(updatedProduct);

        if (updateProduct == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    public ResponseEntity<Boolean> deleteProduct(@RequestBody Product deletedProduct) {
        try {
            productService.deleteProduct(deletedProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(deletedProduct));
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
