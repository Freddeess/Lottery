package com.example.LIAXLENT.Lottery.repositories;

import com.example.LIAXLENT.Lottery.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
