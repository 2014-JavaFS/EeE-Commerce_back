package com.revature.eeecommerce.Order;

import com.revature.eeecommerce.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

//JPA
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Timestamp date;
}
