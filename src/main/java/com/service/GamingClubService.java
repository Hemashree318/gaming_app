package com.example.gamingclub.service;

import java.util.List;
import com.example.gamingclub.model.Member;
import com.example.gamingclub.model.Recharge;
import com.example.gamingclub.model.Game;
import com.example.gamingclub.repository.MemberRepository;
import com.example.gamingclub.repository.RechargeRepository;
import com.example.gamingclub.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GamingClubService {

    private final MemberRepository memberRepository;
    private final RechargeRepository rechargeRepository;
    private final GameRepository gameRepository;

    public GamingClubService(MemberRepository memberRepository,
                             RechargeRepository rechargeRepository,
                             GameRepository gameRepository) {
        this.memberRepository = memberRepository;
        this.rechargeRepository = rechargeRepository;
        this.gameRepository = gameRepository;
    }

    // ---------------------
    // Member methods
    // ---------------------
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Recharge rechargeMember(String memberId, double amount) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Recharge recharge = new Recharge();
        recharge.setMember(member);
        recharge.setAmount(amount);
        recharge.setDate(LocalDateTime.now());

        return rechargeRepository.save(recharge);
    }

    // ---------------------
    // Game methods
    // ---------------------
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // ---------------------
    // Update Member Attributes
    // ---------------------
    public Member updateMember(String memberId, Member updatedFields) {
        // Fetch existing member
        Member existing = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Update only the fields provided
        if (updatedFields.getName() != null && !updatedFields.getName().isBlank()) {
            existing.setName(updatedFields.getName());
        }
        if (updatedFields.getPhoneNumber() != null && !updatedFields.getPhoneNumber().isBlank()) {
            existing.setPhoneNumber(updatedFields.getPhoneNumber());
        }
        if (updatedFields.getBalance() >= 0) {
            existing.setBalance(updatedFields.getBalance());
        }

        return memberRepository.save(existing);
    }
}

