package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.User.User;
import com.revature.eeecommerce.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    /*
    TODO: As a User, I should be able to add items to my cart that I will later purchase or remove from my cart
        - Customer can:
            - View current user's cart
            - Add item(s) to cart
                - Duplicate or different items
            - Remove item(s) from a cart
                - 1+ of the same item at a time
            - View total price of cart
                - Total for each item duplicate
        - Employee can:
            - View a specific user's cart
            - Retrieve total number of active carts
            - View cart (testing purposes)
        - Idea: Cart time-out after 1 week of not checking out

     TODO: As a User, I should be able to checkout with the items in my cart, purchasing them and removing them from the inventory
        - Connected with order items and orders tables
     */

    // get cart by user (authorize logged-in user)
    // get all carts
    @GetMapping
    private List<Cart> getCartByUserId(@RequestHeader int userId, @RequestHeader String userType){
        if (userId == 0) {
            throw new DataNotFoundException("This user does not exist or is not logged in");
        }
        else if(userType.equals("EMPLOYEE")) {
            return cartService.findAllCarts();
        } else {
            return cartService.findCartByUserId(userId);
        }
    }
    // post item to cart
    // update cart - Add more items
    @PostMapping
    private Cart postCart(@RequestHeader int userId, @RequestBody Cart cart) {
        if (userId == 0) {
            throw new DataNotFoundException("This user does not exist or is not logged in");
        } else {
            return cartService.postCart(userId, cart);
        }
    }

    // delete by user id
    @DeleteMapping("/{user_id}")
    private Integer deleteCart(@PathVariable int user_id) {
        return cartService.deleteCartById(user_id);
    }

    @GetMapping("/{user_id}")
    private Integer getCartTotal(@RequestHeader int userId, @PathVariable int user_id){
        if (userId == 0) {
            throw new DataNotFoundException("This user does not exist or is not logged in");
        } else if (userId != user_id){
            throw new DataNotFoundException("Incorrect user");
        } else {
            return cartService.getCartTotal(userId);
        }
    }
    // delete by product id and user id
    // get cart price
    // get cart price by product id
    // get total active carts

}
