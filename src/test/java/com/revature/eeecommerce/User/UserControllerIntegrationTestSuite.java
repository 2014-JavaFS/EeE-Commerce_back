package com.revature.eeecommerce.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTestSuite {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private static User defaultUser = new User(1, "Amsal", "Kassam", "12345 Fake St.", "test@email.com", "Password123", User.userType.EMPLOYEE);
    private static String userJSON = "{\"userId\":1,\"firstName\":\"Amsal\",\"lastName\":\"Kassam\",\"address\":\"12345 Fake St.\",\"email\":\"test@email.com\",\"password\":\"Password123\",\"userType\":\"EMPLOYEE\"}";

    @Test
    public void testGetFindAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(List.of(defaultUser));
        String expectedResult = "[" + userJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .header("userType", "EMPLOYEE"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetFindUserById() throws Exception {
        when(userService.findById(1)).thenReturn(defaultUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.create(defaultUser)).thenReturn(defaultUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.update(defaultUser)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(userService.delete(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }
}
