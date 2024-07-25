package com.revature.eeecommerce.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arjun Ramsinghani
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
