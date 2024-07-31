package com.revature.eeecommerce.User;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Searches for a User by email and password
     *
     * @param email - String
     * @param password - String
     * @return User object if found, else null and throws AuthException
     */
    public User findByEmailAndPassword(String email, String password) throws AuthenticationException {
        return userRepository.findbyEmailAndPassword(email, password).orElseThrow(() -> new AuthenticationException());
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id)throws RuntimeException {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Nothing in the database with ID of " + id));
    }

    public boolean update(User user) {
        userRepository.save(user);
        return true;
    }

    public boolean delete(int id) {
        userRepository.deleteById(id);
        return true;
    }
}
