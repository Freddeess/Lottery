package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Account;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.services.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
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
}
