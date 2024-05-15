package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (employeeService.verifyLogin(email, password)) {
            return ResponseEntity.ok("Inloggningen lyckades!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inloggningen misslyckades.");
        }
    }
}

