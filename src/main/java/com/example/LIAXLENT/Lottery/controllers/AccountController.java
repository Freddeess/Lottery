package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Account;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.services.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AccountController {
//Testar
    
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/account/employees/{employeeId}")
    public Account findAccount(@PathVariable (value = "employeeId") int employeeId){
        return accountService.findAccountByEmployeeId(employeeId);
    }
    @GetMapping("/my-account")
    public ResponseEntity<?> findMyAccount(HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        Account account = accountService.findAccountByEmployeeId(loggedInUser.getId());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/my-balance")
    public ResponseEntity<?> findMyBalance(HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        Integer balance = accountService.findBalanceByEmployeeId(loggedInUser.getId());
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
    //Funktioner för eventuell Admin:
    @PostMapping("/account/employees/{employeeId}")
    public Account createAccount(@PathVariable(value = "employeeId") int employeeId){
        return accountService.createAccount(employeeId);
    }

    @PatchMapping("/account/{id}")
    public Account updateBalance(@PathVariable(value = "id") int id,
                                 @RequestParam (value = "balance", required = true) int balance){
        return accountService.updateBalance(id,balance);

    }
}
