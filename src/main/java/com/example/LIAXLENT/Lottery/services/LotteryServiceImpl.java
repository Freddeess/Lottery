package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryServiceImpl implements LotteryService{

    private final LotteryRepository lotteryRepository;

    @Autowired
    public LotteryServiceImpl(LotteryRepository lotteryRepository){
        this.lotteryRepository = lotteryRepository;
    }
    @Override
    public List<Lottery> findAll(){
        return lotteryRepository.findAll();
    }

    @Override
    public Lottery findById(int id){
        Optional<Lottery> lot = lotteryRepository.findById(id);
        Lottery lottery = null;
        if (lot.isPresent()){
            lottery = lot.get();
        }
        else {
            throw new RuntimeException("Lotteri med id "+id+" hittades inte");
        }
        return lottery;
    }

    @Override
    public Lottery save (Lottery lottery){
        return lotteryRepository.save(lottery);
    }

    @Override
    public void deleteById(int id){
        Optional<Lottery> lot = lotteryRepository.findById(id);
        Lottery lottery = null;
        if (lot.isPresent()){
            lottery = lot.get();
        }
        else {
            throw new RuntimeException("Lotteri med id "+id+" hittades inte");
        }
        lotteryRepository.deleteById(id);
    }
}
