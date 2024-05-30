package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();

    Optional<Account> findById(int id);

    Account createAccount (int employeeId);

    Account updateBalance(int id, int balance);

    void deleteById(int id);

    Account findAccountByEmployeeId(int employeeId);

    Integer findBalanceByEmployeeId(int employeeId);
}
