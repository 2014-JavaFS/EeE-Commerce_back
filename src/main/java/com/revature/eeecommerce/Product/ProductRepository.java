package com.revature.eeecommerce.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arjun Ramsinghani
 * The Repository will use Jpa Repository to use its methods for database connections.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
