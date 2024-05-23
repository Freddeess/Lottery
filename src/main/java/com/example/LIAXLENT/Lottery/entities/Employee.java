package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "xlent_coins")
    private int xlentCoins;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(targetEntity = Lottery.class, mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lottery> lotteries;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getXlentCoins() {
        return xlentCoins;
    }

    public void setXlentCoins(int xlentCoins) {
        this.xlentCoins = xlentCoins;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
