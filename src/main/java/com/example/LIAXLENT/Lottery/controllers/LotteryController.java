package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.services.LotteryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
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
    public List<Lottery> findAllLotteries(@RequestParam (value = "active", required = false) Boolean active){
        if (active != null && active){
            return lotteryService.findActiveLotteries();
        }
        if ( active != null && !active){
            return lotteryService.findInactiveLotteries();
        }
        else {
            return lotteryService.findAll();
        }
    }
    
    @GetMapping("/lotteries/{id}")
    public Lottery findLottery(@PathVariable int id) {
        return lotteryService.findById(id);
    }

    @GetMapping("/lotteries/categories/{categoryId}")
    public List<Lottery> findLotteriesByCategory(@PathVariable (value = "categoryId") int categoryId,
                                                 @RequestParam (value = "active", required = false) Boolean active){
        if(active != null && active){
            return lotteryService.findActiveLotteriesByCategoryId(categoryId);
        }
        else{
            return lotteryService.findLotteriesByCategoryId(categoryId);
        }

    }
    @GetMapping("/my-lotteries")
    public ResponseEntity<?> findMyLotteries(HttpSession session,
                                             @RequestParam (value = "active", required = false) Boolean active) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        else if (active != null && active){
            List<Lottery> lotteries = lotteryService.findActiveLotteriesByEmployeeId(loggedInUser.getId());
            return new ResponseEntity<>(lotteries, HttpStatus.OK);
        }
        else {
            List<Lottery> lotteries = lotteryService.findLotteriesByEmployeeId(loggedInUser.getId());
            return new ResponseEntity<>(lotteries, HttpStatus.OK);
        }

    }

     @PostMapping("/lotteries/employees/{employeeId}/categories/{categoryId}")
    public Lottery createLottery(@PathVariable (value = "employeeId") int employeeId,
                                 @PathVariable (value = "categoryId") int categoryId,
                                 @RequestBody Lottery lottery){
        return lotteryService.createLottery(employeeId, lottery,categoryId);
    }

    @PostMapping("/lotteries/categories/{categoryId}")
    public ResponseEntity<?>  createLottery(HttpSession session, @RequestBody Lottery lottery,
                                            @PathVariable (value = "categoryId") int categoryId) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        Lottery createdLottery = lotteryService.createLottery(loggedInUser.getId(), lottery, categoryId);
        return new ResponseEntity<>(createdLottery, HttpStatus.CREATED);
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

