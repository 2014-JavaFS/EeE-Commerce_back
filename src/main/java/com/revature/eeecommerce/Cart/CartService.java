package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.Product.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService{
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public List<Cart> findCartByUserId(int id) {
        return cartRepository.findCartByUserId(id);
    }

    public Cart postCart(int id, Cart cart) {
        Cart dupe = findCart(id, cart);
        if(dupe == null) {
            return cartRepository.save(cart);
        } else {
            return cartRepository.updateCart(dupe.getUser(), dupe.getProduct(), dupe.getCount());
        }
    }

    private Cart findCart(int id, Cart cart) {
        Optional<Cart> cartOptional = cartRepository.findCartByUserAndProduct(id, cart.getProduct());
        return cartOptional.orElse(null);
    }

    @Transactional
    public Integer deleteCartById(int userId) {
        List<Cart> cartList = cartRepository.findCartByUserId(userId);
        if(!cartList.isEmpty()) {
            cartRepository.deleteByUserId(userId);
            return 1;
        } else {
            return null;
        }
    }

    public Integer getCartTotal(int userId) {
        return cartRepository.getCartTotal(userId);
    }
}
