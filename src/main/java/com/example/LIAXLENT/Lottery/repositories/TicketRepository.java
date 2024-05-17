package com.example.LIAXLENT.Lottery.repositories;

import com.example.LIAXLENT.Lottery.entities.Lottery;
import com.example.LIAXLENT.Lottery.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByLottery(Lottery lottery);

}