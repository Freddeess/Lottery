package com.example.LIAXLENT.Lottery.controllers;


import com.example.LIAXLENT.Lottery.entities.PaymentMethod;
import com.example.LIAXLENT.Lottery.services.PaymentMethodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService){
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/paymentMethods")
    public List<PaymentMethod> findAllPaymentMethods(){
        return paymentMethodService.findAll();
    }
}
