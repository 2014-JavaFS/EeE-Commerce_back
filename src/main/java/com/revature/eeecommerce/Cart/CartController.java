package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.util.exceptions.DataNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // get cart by user (authorize logged-in user)
    // get all carts
    @GetMapping
    private ResponseEntity<List<Cart>> getCartByUserId(@Valid @RequestHeader int userId, @RequestHeader String userType){
        if (userId == 0) {
            throw new DataNotFoundException("This user does not exist or is not logged in");
        }
        else if(userType.equals("EMPLOYEE")) {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.findAllCarts());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartByUserId(userId));
        }
    }
    // post item to cart
    // update cart - Add more items
    @PostMapping
    private ResponseEntity<Cart> postCart(@Valid @RequestHeader int userId, @RequestBody Cart cart) {
        if (userId == 0) {
            throw new DataNotFoundException("This user does not exist or is not logged in");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.postCart(userId, cart));
        }
    }

    // delete by user id
    @DeleteMapping()
    private ResponseEntity<Boolean> deleteCart(@Valid @RequestHeader int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteCart(userId));
    }

//    @GetMapping("/total")
//    private ResponseEntity<Integer> getCartTotal(@Valid @RequestHeader int userId){
//        if (userId == 0) {
//            throw new DataNotFoundException("This user does not exist or is not logged in");
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartTotal(userId));
//        }
//    }
}
