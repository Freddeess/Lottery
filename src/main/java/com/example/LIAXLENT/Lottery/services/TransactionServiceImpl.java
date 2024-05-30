package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Transaction;
import com.example.LIAXLENT.Lottery.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Override
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    @Override
    public Optional<Transaction> findById(int id){
        return transactionRepository.findById(id);
    }
    @Override
    public Transaction save(Transaction category){
        return transactionRepository.save(category);
    }
    @Override
    public void deleteById(int id){
        transactionRepository.deleteById(id);
    }
}
