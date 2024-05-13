package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Lottery;

import java.util.List;

public interface LotteryService {

    List<Lottery> findAll();

    Lottery findById(int id);

    Lottery save (Lottery lottery);

    void deleteById(int id);
}
