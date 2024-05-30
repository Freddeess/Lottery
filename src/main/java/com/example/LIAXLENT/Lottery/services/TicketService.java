package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Ticket;
import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket findById(int id);

    List<Ticket> findWinningTickets();

    Ticket createTicket (int employeeId, int lotteryId, int paymentMethodId);

    void deleteById(int id);

    List<Ticket> findByEmployeeId(int employeeId);

    List<Ticket> findActiveTickets();

    List<Ticket> findByEmployeeIdAndLotteryActiveTrue(int employeeId);

    List<Ticket> findByEmployeeIdAndWinnerIsTrue(int employeeId);

}
