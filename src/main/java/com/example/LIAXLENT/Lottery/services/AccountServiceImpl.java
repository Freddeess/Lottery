package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Account;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.repositories.AccountRepository;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final EmployeeRepository employeeRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              EmployeeRepository employeeRepository){
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    @Override
    public Optional<Account> findById(int id){
        return accountRepository.findById(id);
    }
    @Override
    public Account createAccount (int employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Anställd med id "+ employeeId +" hittades inte"));
        Account account = new Account();
        account.setEmployee(employee);
        return accountRepository.save(account);
    }
    @Override
    public Account updateBalance(int accountId, int balance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Konto med id " + accountId + " hittades inte"));
        account.setBalance(balance);
        return accountRepository.save(account);
    }
    @Override
    public void deleteById(int id){
        accountRepository.deleteById(id);
    }
    @Override
    public Account findAccountByEmployeeId(int employeeId){
        return accountRepository.findAccountByEmployeeId(employeeId);
    }
    @Override
    public Integer findBalanceByEmployeeId(int employeeId){
        return accountRepository.findAccountByEmployeeId(employeeId).getBalance();
    }
}
