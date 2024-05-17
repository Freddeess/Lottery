package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.services.LotteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LotteryController {

    private final LotteryService lotteryService;
    public LotteryController(LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }
    @GetMapping("/lotteries")
    public List<Lottery> findAllLotteries(){
        return lotteryService.findAll();
    }
    @GetMapping("/lotteries/{id}")
    public Lottery findLottery(@PathVariable int id) {
        return lotteryService.findById(id);
    }
    @PostMapping("/lotteries/employees/{employeeId}")
    public Lottery createLottery(@PathVariable (value = "employeeId") int employeeId,
                                 @RequestBody Lottery lottery){
        return lotteryService.createLottery(employeeId, lottery);
    }
    @PutMapping("/lotteries/{id}")
    public Lottery updateLottery(@PathVariable int id, @RequestBody Lottery updatedLottery){
        Lottery lottery = lotteryService.findById(id);
        lottery.setName(updatedLottery.getName());
        lottery.setDescription(updatedLottery.getDescription());
        lottery.setPriceSEK(updatedLottery.getPriceSEK());
        lottery.setPriceXlentCoins(updatedLottery.getPriceXlentCoins());
        lottery.setActive(updatedLottery.isActive());
        return lotteryService.save(lottery);
    }
    @DeleteMapping("/lotteries/{id}")
    public void deleteLottery(@PathVariable int id){
        lotteryService.deleteById(id);
    }
    @GetMapping("/lotteries/{id}/draw")
    public ResponseEntity<?> drawWinner(@PathVariable int id) {
        try {
            Ticket winnerTicket = lotteryService.drawWinner(id);
            Employee winner = winnerTicket.getEmployee();
            return ResponseEntity.ok("Grattis " + winner.getFirstName() + " " + winner.getLastName() + "! Du är vinnaren av lotteriet med ID " + id + ".");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

