package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;

import java.util.List;

public interface LotteryService {

    List<Lottery> findAll();

    List<Lottery> findActiveLotteries();

    List<Lottery> findInactiveLotteries();

    Lottery findById(int id);

    Lottery createLottery (int id,Lottery lottery);

    Lottery save(Lottery lottery);
    void deleteById(int id);

    Ticket drawWinner(int lotteryId);
}
