package com.example.gamingclub.service;

import java.util.List;
import com.example.gamingclub.model.Member;
import com.example.gamingclub.model.Recharge;
import com.example.gamingclub.repository.MemberRepository;
import com.example.gamingclub.repository.RechargeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GamingClubService {

    private final MemberRepository memberRepository;
    private final RechargeRepository rechargeRepository;

    public GamingClubService(MemberRepository memberRepository, RechargeRepository rechargeRepository) {
        this.memberRepository = memberRepository;
        this.rechargeRepository = rechargeRepository;
    }

    // ✅ Add new member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
    
    public List<Member> getAllMembers() {
    return memberRepository.findAll();  // Assuming you have a MongoRepository
    }


    // ✅ Recharge existing member
    public Recharge rechargeMember(String memberId, double amount) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Recharge recharge = new Recharge();
        recharge.setMember(member);
        recharge.setAmount(amount);
        recharge.setDate(LocalDateTime.now());

        return rechargeRepository.save(recharge);
    }
}
