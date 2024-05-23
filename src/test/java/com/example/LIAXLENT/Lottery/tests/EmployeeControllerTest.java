package com.example.LIAXLENT.Lottery.tests;

import com.example.LIAXLENT.Lottery.controllers.EmployeeController;
import com.example.LIAXLENT.Lottery.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testRegisterUserSuccess() throws Exception {
        String email = "test@example.com";
        String password = "newPassword";

        // Mock the service method
        Mockito.doNothing().when(employeeService).registerUser(email, password);

        // Perform the request and verify the response
        mockMvc.perform(post("/api/register")
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Registrering lyckades!"));

        // Verify that the service method was called
        Mockito.verify(employeeService).registerUser(email, password);
    }

    @Test
    public void testRegisterUserNotFound() throws Exception {
        String email = "notfound@example.com";
        String password = "newPassword";

        // Mock the service method to throw an exception
        Mockito.doThrow(new RuntimeException("Användare med email " + email + " hittades inte"))
                .when(employeeService).registerUser(email, password);

        // Perform the request and verify the response
        mockMvc.perform(post("/api/register")
                        .param("email", email)
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Användare med email " + email + " hittades inte"));

        // Verify that the service method was called
        Mockito.verify(employeeService).registerUser(email, password);
    }
}

