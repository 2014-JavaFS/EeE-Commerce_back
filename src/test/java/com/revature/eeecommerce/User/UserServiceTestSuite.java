package com.revature.eeecommerce.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {
    @Mock
    private UserRepository mockRepo;

    @InjectMocks
    private UserService userService;

    private static User defaultUser = new User(1, "Amsal", "Kassam", "12345 Fake St.", "test@email.com", "Password123", User.userType.EMPLOYEE);

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>(Arrays.asList(defaultUser));
        when(mockRepo.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        assertEquals(1, result.size());
        assertEquals(defaultUser, result.get(0));
    }

    @Test
    public void testGetUserById() {
        when(mockRepo.findByUserId(defaultUser.getUserId())).thenReturn(Optional.ofNullable(defaultUser));

        User result = userService.findById(defaultUser.getUserId());
        assertEquals(defaultUser, result);
    }

    @Test
    public void testGetUserByLoginInfo() {
        when(mockRepo.findByEmailAndPassword(defaultUser.getEmail(), defaultUser.getPassword()))
                .thenReturn(Optional.ofNullable(defaultUser));

        final User[] result = new User[1];
        assertDoesNotThrow(() -> {
            result[0] = userService.findByEmailAndPassword(defaultUser.getEmail(), defaultUser.getPassword());
        });
        assertEquals(defaultUser, result[0]);
    }

    @Test
    public void testCreateUser() {
        when(mockRepo.save(defaultUser)).thenReturn(defaultUser);

        User result = userService.create(defaultUser);
        assertEquals(defaultUser, result);
    }

    @Test
    public void testUpdateUser() {
        when(mockRepo.save(defaultUser)).thenReturn(defaultUser);

        assertTrue(userService.update(defaultUser));
        verify(mockRepo, times(1)).save(defaultUser);
    }

    public void testDeleteUser() {
        assertTrue(userService.delete(defaultUser.getUserId()));
        verify(mockRepo, times(1)).deleteById(defaultUser.getUserId());
    }
}
