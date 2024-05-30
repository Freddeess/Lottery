package com.example.LIAXLENT.Lottery.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @Column(name = "SEK")
    private int sek;

    @Column(name = "xlent_coins")
    private int xlentCoins;

    @Column(name = "date")
    private Timestamp date;
    @PrePersist
    protected void onCreate(){
        date = new Timestamp(System.currentTimeMillis());
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Transaction(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSek() {
        return sek;
    }

    public void setSek(int sek) {
        this.sek = sek;
    }

    public int getXlentCoins() {
        return xlentCoins;
    }

    public void setXlentCoins(int xlentCoins) {
        this.xlentCoins = xlentCoins;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
