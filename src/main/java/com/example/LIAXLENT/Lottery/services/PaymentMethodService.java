package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {

    List<PaymentMethod> findAll();

    Optional<PaymentMethod> findById(int id);

    PaymentMethod save (PaymentMethod paymentMethod);

    void deleteById(int id);
}
