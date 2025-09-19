package com.example.gamingclub.repository;

import com.example.gamingclub.model.Recharge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RechargeRepository extends MongoRepository<Recharge, String> {
}
