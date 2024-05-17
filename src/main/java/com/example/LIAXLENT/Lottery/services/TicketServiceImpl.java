package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import com.example.LIAXLENT.Lottery.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final LotteryRepository lotteryRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             LotteryRepository lotteryRepository,
                             EmployeeRepository employeeRepository){
        this.ticketRepository = ticketRepository;
        this.lotteryRepository = lotteryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findWinningTickets(){
        return ticketRepository.getTicketsByWinnerIsTrue();
    }

    @Override
    public Ticket findById(int id){
        Optional<Ticket> tick = ticketRepository.findById(id);
        Ticket ticket = null;
        if (tick.isPresent()){
            ticket = tick.get();
        }
        else {
            throw new RuntimeException("Lott med id "+id+" hittades inte");
        }
        return ticket;
    }

    @Override
    public Ticket save (int employeeId, int lotteryId){
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        Employee employee = null;
        if(emp.isPresent()){
            employee = emp.get();
        }
        else{
            throw new RuntimeException("Anställd med id "+employeeId+" hittades inte");
        }

        Optional<Lottery> lot = lotteryRepository.findById(lotteryId);
        Lottery lottery = null;
        if(lot.isPresent()){
            lottery = lot.get();
        }
        else{
            throw new RuntimeException("Lott med id "+lotteryId+" hittades inte");
        }

        if(employee.getXlentCoins()>=lottery.getPriceXlentCoins()){
            employee.setXlentCoins(employee.getXlentCoins()-lottery.getPriceXlentCoins());
            lottery.getEmployee().setXlentCoins(
                    lottery.getPriceXlentCoins() +
                    lottery.getEmployee().getXlentCoins());
        }
        else {
            throw new RuntimeException("Det saknas " +
                    (employee.getXlentCoins()-lottery.getPriceXlentCoins())+
                    " XlentCoins för att utföra köpet");
        }
        Ticket ticket = new Ticket();
        ticket.setEmployee(employee);
        ticket.setLottery(lottery);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(int id){
        Optional<Ticket> tick = ticketRepository.findById(id);
        Ticket ticket = null;
        if (tick.isPresent()){
            ticket = tick.get();
        }
        else {
            throw new RuntimeException("Lott med id "+id+" hittades inte");
        }
        ticketRepository.deleteById(id);
    }
}
