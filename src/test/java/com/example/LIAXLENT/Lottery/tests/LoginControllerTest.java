package com.example.LIAXLENT.Lottery.tests;

import com.example.LIAXLENT.Lottery.controllers.LoginController;
import com.example.LIAXLENT.Lottery.services.EmployeeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testLoginSuccess() throws Exception {
        String email = "test@example.com";
        String password = "password123";

        // Configure mock to return true when the correct email and password are provided
        given(employeeService.verifyLogin(email, password)).willReturn(true);

        mockMvc.perform(post("/api/login")
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Inloggningen lyckades!"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        String email = "wrong@example.com";
        String password = "wrongpassword";

        // Configure mock to return false for incorrect credentials
        given(employeeService.verifyLogin(email, password)).willReturn(false);

        mockMvc.perform(post("/api/login")
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Inloggningen misslyckades."));
    }
}

