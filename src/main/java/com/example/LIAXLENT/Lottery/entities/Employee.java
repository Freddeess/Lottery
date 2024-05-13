package com.example.LIAXLENT.Lottery.entities;

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

    @Column(name = "email")
    private String email;

    @Column(name = "xlent_coins")
    private int xlentCoins;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

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
}
