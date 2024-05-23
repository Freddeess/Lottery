package com.example.LIAXLENT.Lottery.tests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.LIAXLENT.Lottery.controllers.LotteryController;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.services.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

public class LotteryControllerTest {

    @Mock
    private LotteryService lotteryService;

    private MockMvc mockMvc;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LotteryController controller = new LotteryController(lotteryService);
        mockMvc = standaloneSetup(controller).build();

        // Set up a mock session
        session = new MockHttpSession();
        Employee loggedInUser = new Employee();
        loggedInUser.setId(1);
        loggedInUser.setEmail("test@example.com");
        session.setAttribute("loggedInUser", loggedInUser);
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

    @Test
    public void testFindMyLotteries() throws Exception {
        // Mock the service call
        Lottery lottery1 = new Lottery();
        lottery1.setId(1);
        lottery1.setName("Test Lottery 1");

        Lottery lottery2 = new Lottery();
        lottery2.setId(2);
        lottery2.setName("Test Lottery 2");

        List<Lottery> myLotteries = Arrays.asList(lottery1, lottery2);

        when(lotteryService.findLotteriesByEmployeeId(1)).thenReturn(myLotteries);

        // Perform the GET request
        mockMvc.perform(get("/api/my-lotteries").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Lottery 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Lottery 2"));

        // Verify the service call
        verify(lotteryService, times(1)).findLotteriesByEmployeeId(1);
    }

    @Test
    public void testFindMyLotteriesWithoutLogin() throws Exception {
        // Perform the GET request without a logged-in user
        mockMvc.perform(get("/api/my-lotteries"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Ingen användare är inloggad."));
    }
}
