package com.example.gamingclub.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Document(collection = "recharges")
public class Recharge {

    @Id
    private String id;

    @DBRef
    private Member member;

    @Max(value = 100, message = "Recharge amount cannot exceed 100")
    @Min(value = 1, message = "Recharge amount must be at least 1")
    private double amount;

    private LocalDateTime date;

    public Recharge() {}

    public Recharge(Member member, double amount) {
        this.member = member;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
