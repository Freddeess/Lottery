package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "lotteries")
public class Lottery {

    /*public Lottery(){
        this.active = true;
    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lottery_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price_SEK")
    private int priceSEK;

    @Column(name = "price_XlentCoins")
    private int priceXlentCoins;

    @Column(name = "active")
    private boolean active = true;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "lottery", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(int priceSEK) {
        this.priceSEK = priceSEK;
    }

    public int getPriceXlentCoins() {
        return priceXlentCoins;
    }

    public void setPriceXlentCoins(int priceXlentCoins) {
        this.priceXlentCoins = priceXlentCoins;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

