package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import com.example.LIAXLENT.Lottery.services.LotteryService;
import com.example.LIAXLENT.Lottery.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    @GetMapping("/tickets")
    public List<Ticket> findAllTickets(@RequestParam(value = "winner", required = false) Boolean winner){
        if (winner != null && winner ){
            return ticketService.findWinningTickets();
        }
        else{
            return ticketService.findAll();
        }
    }
    @GetMapping("/tickets/{id}")
    public Ticket findTicket(@PathVariable int id) {
        return ticketService.findById(id);
    }

    @PostMapping("/tickets/employees/{employeeId}/lotteries/{lotteryId}")
    public Ticket createTicket(@PathVariable (value = "employeeId") int employeeId,
                               @PathVariable (value = "lotteryId") int lotteryId){
        return ticketService.save(employeeId, lotteryId);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable int id){
        ticketService.deleteById(id);
    }
}
