package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    public PaymentMethod(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
