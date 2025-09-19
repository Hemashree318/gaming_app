package com.example.gamingclub.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Indexed(unique = true)
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @Min(value = 0, message = "Balance cannot be less than 0")
    @Max(value = 10000, message = "Balance cannot exceed 10000")
    private double balance;

    public Member() {}

    public Member(String name, String phoneNumber, double balance) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    // getters and setters
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
