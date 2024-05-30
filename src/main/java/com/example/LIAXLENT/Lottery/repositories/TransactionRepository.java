package com.example.LIAXLENT.Lottery.repositories;

import com.example.LIAXLENT.Lottery.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
