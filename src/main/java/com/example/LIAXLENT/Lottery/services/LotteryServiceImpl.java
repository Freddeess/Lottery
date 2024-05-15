package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import com.example.LIAXLENT.Lottery.repositories.LotteryRepository;
import com.example.LIAXLENT.Lottery.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LotteryServiceImpl implements LotteryService {

    private final LotteryRepository lotteryRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public LotteryServiceImpl(LotteryRepository lotteryRepository, TicketRepository ticketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Lottery> findAll() {
        return lotteryRepository.findAll();
    }

    @Override
    public Lottery findById(int id) {
        Optional<Lottery> lottery = lotteryRepository.findById(id);
        if (!lottery.isPresent()) {
            throw new RuntimeException("Lotteri med id " + id + " hittades inte");
        }
        return lottery.get();
    }

    @Override
    public Lottery save(Lottery lottery) {
        return lotteryRepository.save(lottery);
    }

    @Override
    public void deleteById(int id) {
        if (!lotteryRepository.existsById(id)) {
            throw new RuntimeException("Lotteri med id " + id + " hittades inte");
        }
        lotteryRepository.deleteById(id);
    }

    @Override
    public Ticket drawWinner(int lotteryId) {
        Optional<Lottery> lotteryOpt = lotteryRepository.findById(lotteryId);
        if (!lotteryOpt.isPresent() || !lotteryOpt.get().isActive()) {
            throw new RuntimeException("Aktiv lottning med id " + lotteryId + " hittades inte");
        }

        Lottery lottery = lotteryOpt.get();
        List<Ticket> tickets = ticketRepository.findByLottery(lottery);
        if (tickets.isEmpty()) {
            throw new RuntimeException("Inga biljetter finns för lottningen");
        }

        Ticket winner = tickets.get(new Random().nextInt(tickets.size()));
        lottery.setActive(false);
        lotteryRepository.save(lottery);
        return winner;
    }
}
