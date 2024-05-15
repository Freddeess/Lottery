package com.example.LIAXLENT.Lottery.tests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.LIAXLENT.Lottery.controllers.LotteryController;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.services.LotteryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

public class LotteryControllerTest {

    @Mock
    private LotteryService lotteryService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LotteryController controller = new LotteryController(lotteryService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testDrawWinner() throws Exception {
        // Förbered data
        Ticket winner = new Ticket();
        Employee winnerEmployee = new Employee();
        winnerEmployee.setFirstName("John");
        winnerEmployee.setLastName("Doe");
        winner.setEmployee(winnerEmployee);

        when(lotteryService.drawWinner(1)).thenReturn(winner);

        // Utför testet
        mockMvc.perform(get("/api/lotteries/1/draw"))
                .andExpect(status().isOk())
                .andExpect(content().string("Grattis John Doe! Du är vinnaren av lotteriet med ID 1."));

        // Verifiera att servicen anropades korrekt
        verify(lotteryService).drawWinner(1);
    }
}

