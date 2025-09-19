package com.example.gamingclub.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "games")
public class Game {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Game name cannot be blank")
    private String name;

    @Positive(message = "Price must be positive")
    private double price; // entry fee

    private String description;

    // Link to members by their id (acts like a "foreign-key" list)
    private List<String> registeredMemberIds = new ArrayList<>();

    public Game() {}

    public Game(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // getters / setters
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getRegisteredMemberIds() { return registeredMemberIds; }
    public void setRegisteredMemberIds(List<String> registeredMemberIds) { this.registeredMemberIds = registeredMemberIds; }

    // helper methods
    public void addRegisteredMemberId(String memberId) {
        if (registeredMemberIds == null) registeredMemberIds = new ArrayList<>();
        if (!registeredMemberIds.contains(memberId)) registeredMemberIds.add(memberId);
    }

    public boolean isMemberRegistered(String memberId) {
        return registeredMemberIds != null && registeredMemberIds.contains(memberId);
    }
}
