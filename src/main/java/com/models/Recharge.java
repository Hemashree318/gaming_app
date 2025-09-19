package com.example.gamingclub.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "recharges")
public class Recharge {

    @Id
    private String id;

    @NotBlank(message = "Member ID cannot be blank")
    private String memberId;  // acts like a foreign key to Member._id

    @Max(value = 100, message = "Recharge amount cannot exceed 100")
    @Min(value = 1, message = "Recharge amount must be at least 1")
    private double amount;

    private LocalDateTime date;

    public Recharge() {}

    public Recharge(String memberId, double amount) {
        this.memberId = memberId;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
