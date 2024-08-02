package com.revature.eeecommerce.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @GetMapping("/{userId}")
    private ResponseEntity<User> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    private ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(user);
    }
}
