package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Ticket;

import com.example.LIAXLENT.Lottery.services.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    @GetMapping("/tickets")
    public List<Ticket> findAllTickets(@RequestParam(value = "winner", required = false) Boolean winner,
                                       @RequestParam(value = "active", required = false) Boolean active){
        if (winner != null && winner ){
            return ticketService.findWinningTickets();
        }
        if (active != null && active){
            return ticketService.findActiveTickets();
        }
        else{
            return ticketService.findAll();
        }
    }


    @GetMapping("/my-tickets")
    public List<Ticket> findMyTickets(HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return ticketService.findByEmployeeId(loggedInUser.getId());
        } else {
            throw new RuntimeException("Ingen användare är inloggad.");
        }
    }
    @GetMapping("/tickets/{id}")
    public Ticket findTicket(@PathVariable int id) {
        return ticketService.findById(id);
    }

    @PostMapping("/tickets/employees/{employeeId}/lotteries/{lotteryId}/paymentMethod/{paymentMethodId}")
    public Ticket createTicket(@PathVariable (value = "employeeId") int employeeId,
                               @PathVariable (value = "lotteryId") int lotteryId,
                               @PathVariable(value = "paymentMethodId") int paymentMethodId){
        return ticketService.createTicket(employeeId, lotteryId, paymentMethodId);
    }

    @PostMapping("/tickets/lotteries/{lotteryId}/paymentMethod/{paymentMethodId}")
    public ResponseEntity<?> createTicket(HttpSession session,
                                          @PathVariable (value = "lotteryId") int lotteryId,
                                          @PathVariable(value = "paymentMethodId") int paymentMethodId) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        Ticket createdTicket = ticketService.createTicket(loggedInUser.getId(), lotteryId, paymentMethodId);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable int id){
        ticketService.deleteById(id);
    }


}
