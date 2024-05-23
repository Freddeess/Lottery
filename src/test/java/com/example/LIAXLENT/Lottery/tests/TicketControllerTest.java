package com.example.LIAXLENT.Lottery.tests;

import com.example.LIAXLENT.Lottery.controllers.TicketController;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.services.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
    }

    @Test
    public void testFindMyTickets() throws Exception {
        Employee loggedInUser = new Employee();
        loggedInUser.setId(1);
        loggedInUser.setEmail("john.doe@example.com");
        session.setAttribute("loggedInUser", loggedInUser);

        when(ticketService.findByEmployeeId(loggedInUser.getId())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/tickets/my").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testFindMyTicketsWithoutLogin() throws Exception {
        Exception exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/api/tickets/my"))
                    .andExpect(status().isInternalServerError());
        });

        assertTrue(exception.getCause() instanceof RuntimeException);
        assertEquals("Ingen användare är inloggad.", exception.getCause().getMessage());
    }
}
