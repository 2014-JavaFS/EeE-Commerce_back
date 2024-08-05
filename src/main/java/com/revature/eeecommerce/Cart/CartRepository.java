package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.User.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

    @Query(value = "from Cart c where c.User.user_id = :id", nativeQuery = true)
    List<Cart> findCartByUserId(int id);

    @Query(value = "from Cart c where c.User.user_id = :id and c.Product = :product", nativeQuery = true)
    Optional<Cart> findCartByUserAndProduct(int id, Product product);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Cart c set c.count = c.count + :count where c.user = :user and c.product = :product")
    Cart updateCart(User user, Product product, int count);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from Cart c where c.User.user_id = :id", nativeQuery = true)
    void deleteByUserId(int id);

    @Query(value = "select sum(c.Product.price) from Cart c where c.User.user_id = :id", nativeQuery = true)
    Integer getCartTotal(int id);
}
