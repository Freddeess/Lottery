package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Account;
import com.example.LIAXLENT.Lottery.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
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
    public Account save(Account account){
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(int id){
        accountRepository.deleteById(id);
    }

    @Override
    public Account findAccountByEmployeeId(int employeeId) {
        List<Account> accounts = accountRepository.findByEmployeeId(employeeId);
        if (accounts.size() > 1) {
            throw new RuntimeException("Mer än ett konto hittades med employee ID: " + employeeId);
        }
        if (accounts.isEmpty()) {
            System.out.println("Inga konton hittades för employee ID: " + employeeId);
            return null;
        } else {
            System.out.println("Ett konto hittades för employee ID: " + employeeId);
            return accounts.get(0);
        }
    }


    @Override
    public Integer findBalanceByEmployeeId(int employeeId){
        Account account = findAccountByEmployeeId(employeeId);
        return account != null ? account.getBalance() : null;
    }
}
