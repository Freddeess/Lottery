package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Ticket;
import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket findById(int id);

    Ticket save (int employeeId, int lotteryId);

    void deleteById(int id);
}
