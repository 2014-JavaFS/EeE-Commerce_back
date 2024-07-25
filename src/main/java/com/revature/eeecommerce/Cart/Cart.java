package com.revature.eeecommerce.Cart;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @EmbeddedId
    @Column(unique = true, nullable = false)
    private CartId id;
    private int count;
}
