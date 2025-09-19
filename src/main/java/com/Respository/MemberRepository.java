package com.example.gamingclub.repository;

import com.example.gamingclub.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByPhoneNumber(String phoneNumber);
}
