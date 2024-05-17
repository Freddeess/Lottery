package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "lottery_id")
    private Lottery lottery;

    @Column(name = "winner")
    private boolean winner;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Lottery getLottery() {
        return lottery;
    }
    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
    public boolean isWinner() {
        return winner;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
