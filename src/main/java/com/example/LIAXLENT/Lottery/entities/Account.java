package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = "employee_id"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Column(name = "balance")
    private int balance;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @PreUpdate
    protected void onUpdate(){
        lastUpdate = new Timestamp(System.currentTimeMillis());
    }

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;


    public Account(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
