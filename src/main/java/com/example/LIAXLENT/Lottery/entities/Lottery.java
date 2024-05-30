package com.example.LIAXLENT.Lottery.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "lotteries")
public class Lottery {

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
    private boolean active;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "drawn_at")
    private Timestamp drawnAt;
    @PrePersist
    protected void onCreate(){
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @OneToMany(targetEntity = Ticket.class, mappedBy = "lottery", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Lottery(){
        this.active = true;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDrawnAt() {
        return drawnAt;
    }

    public void setDrawnAt(Timestamp drawnAt) {
        this.drawnAt = drawnAt;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}

