package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> findAll();

    Optional<Transaction> findById(int id);

    Transaction save (Transaction transaction);

    void deleteById(int id);
}
