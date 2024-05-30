package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Category;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.repositories.CategoryRepository;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import com.example.LIAXLENT.Lottery.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LotteryServiceImpl implements LotteryService {

    private final LotteryRepository lotteryRepository;
    private final TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;

    private final CategoryRepository categoryRepository;
    @Autowired
    public LotteryServiceImpl(LotteryRepository lotteryRepository,
                              TicketRepository ticketRepository,
                              EmployeeRepository employeeRepository,
                              CategoryRepository categoryRepository) {
        this.lotteryRepository = lotteryRepository;
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Lottery> findAll() {
        return lotteryRepository.findAll();
    }

    @Override
    public List<Lottery> findActiveLotteries(){
        return lotteryRepository.getLotteriesByActiveIsTrue();
    }

    @Override
    public List<Lottery> findInactiveLotteries(){
        return lotteryRepository.getLotteriesByActiveIsFalse();
    }
    @Override
    public Lottery findById(int id) {
        Optional<Lottery> lottery = lotteryRepository.findById(id);
        if (lottery.isEmpty()) {
            throw new RuntimeException("Lotteri med id " + id + " hittades inte");
        }
        return lottery.get();
    }
    @Override
    public List<Lottery> findLotteriesByEmployeeId(int employeeId) {
        return lotteryRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Lottery> findLotteriesByCategoryId(int categoryId){
        return lotteryRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Lottery> findActiveLotteriesByCategoryId(int categoryId){
        return lotteryRepository.findAllByActiveTrueAndCategoryId(categoryId);
    }

    @Override
    public List<Lottery> findActiveLotteriesByEmployeeId(int employeeId){
        return lotteryRepository.findAllByActiveTrueAndEmployeeId(employeeId);
    }

    @Override
    public Lottery save(Lottery lottery){
        return lotteryRepository.save(lottery);
    }
    @Override
    public Lottery createLottery(int employeeId, Lottery lottery, int categoryId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        Employee employee = null;
        if(emp.isPresent()){
            employee = emp.get();
        }
        else{
            throw new RuntimeException("Anställd med id "+employeeId+" hittades inte");
        }
        lottery.setEmployee(employee);
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Kategorin hittades inte"));
        lottery.setCategory(category);
        return lotteryRepository.save(lottery);
    }

    @Override
    public void deleteById(int id) {
        if (!lotteryRepository.existsById(id)) {
            throw new RuntimeException("Lotteri med id " + id + " hittades inte");
        }
        lotteryRepository.deleteById(id);
    }

    @Override
    public Ticket drawWinner(int lotteryId) {
        Optional<Lottery> lotteryOpt = lotteryRepository.findById(lotteryId);
        if (!lotteryOpt.isPresent() || !lotteryOpt.get().isActive()) {
            throw new RuntimeException("Aktiv lottning med id " + lotteryId + " hittades inte");
        }
        Lottery lottery = lotteryOpt.get();

        List<Ticket> tickets = ticketRepository.findByLottery(lottery);
        if (tickets.isEmpty()) {
            throw new RuntimeException("Inga biljetter finns för lottningen");
        }

        Ticket winner = tickets.get(new Random().nextInt(tickets.size()));
        winner.setWinner(true);
        lottery.setActive(false);
        lottery.setDrawnAt(new Timestamp(System.currentTimeMillis()));
        lotteryRepository.save(lottery);
        return winner;
    }


}
