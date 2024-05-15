package com.example.LIAXLENT.Lottery.tests;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import com.example.LIAXLENT.Lottery.repositories.TicketRepository;
import com.example.LIAXLENT.Lottery.services.LotteryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LotteryServiceImplTest {

    @Mock
    private LotteryRepository lotteryRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private LotteryServiceImpl lotteryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDrawWinner() {
        // Setup - förbered dina testdata
        Lottery lottery = new Lottery();
        lottery.setId(1);
        lottery.setActive(true);

        Ticket ticket = new Ticket();
        ticket.setId(100);
        ticket.setLottery(lottery);

        when(lotteryRepository.findById(1)).thenReturn(Optional.of(lottery));
        when(ticketRepository.findByLottery(lottery)).thenReturn(Arrays.asList(ticket));

        // Action - utför operationen du vill testa
        Ticket result = lotteryService.drawWinner(1);

        // Assert - kontrollera att resultatet är som förväntat
        assertNotNull(result);
        assertEquals(100, result.getId());
        assertFalse(lottery.isActive()); // Kontrollera att lotteriet nu är inaktivt

        // Verify - säkerställ att interaktionerna med mock-objekten gick som planerat
        verify(lotteryRepository).save(lottery);
    }
}
