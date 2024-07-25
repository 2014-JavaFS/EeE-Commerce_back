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
    private CartId id;
    @Column(columnDefinition = "integer default 1 check (count > 0)")
    private int count;
}
