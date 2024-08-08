package com.revature.eeecommerce.Cart;

import com.revature.eeecommerce.Product.Product;
import com.revature.eeecommerce.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.revature.eeecommerce.User.User.userType.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTestSuite {
    @Mock
    private CartRepository mockCartRepository;

    @InjectMocks
    private CartService sut;

    @Test
    public void testAddItemsToCart() {
        Cart validCart = new Cart(1,
                new User(2, "Donkey", "Kong", "1 Hacker Way", "dkong@mail.com", "Pass@123", CUSTOMER),
                new Product(1, 2400, 0.2, "E Shirt", "Let everyone know you love the letter E", 200, "https://th.bing.com/th/id/OIG2.L4QTYfR6oNyS5Jq.QasC?w=270&h=270&c=6&r=0&o=5&pid=ImgGn"),
                200
        );
        when(mockCartRepository.save(validCart)).thenReturn(validCart);

        Cart returnedCart = sut.postCart(validCart);

        assertEquals(validCart, returnedCart);
        verify(mockCartRepository, times(1)).save(validCart);
    }

    @Test
    public void testDeleteCart() {
        int cart_id = 1;
        boolean actual = sut.deleteCart(cart_id);
        assertTrue(actual);
        verify(mockCartRepository, times(1)).deleteById(1);
    }
}
