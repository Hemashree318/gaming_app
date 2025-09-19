package com.example.gamingclub.repository;

import com.example.gamingclub.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {
    Optional<Game> findByName(String name);
}
