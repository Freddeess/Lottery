package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Category;
import com.example.LIAXLENT.Lottery.entities.PaymentMethod;
import com.example.LIAXLENT.Lottery.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }
    @Override
    public List<PaymentMethod> findAll(){

        return paymentMethodRepository.findAll();
    }
    @Override
    public Optional<PaymentMethod> findById(int id){
        return paymentMethodRepository.findById(id);
    }
    @Override
    public PaymentMethod save(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }
    @Override
    public void deleteById(int id){
        paymentMethodRepository.deleteById(id);
    }
}
