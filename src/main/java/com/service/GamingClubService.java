package com.example.gamingclub.service;

import com.example.gamingclub.model.Member;
import com.example.gamingclub.model.Recharge;
import com.example.gamingclub.model.Game;
import com.example.gamingclub.repository.MemberRepository;
import com.example.gamingclub.repository.RechargeRepository;
import com.example.gamingclub.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // ------------------- Member Methods -------------------
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    // ------------------- Recharge Methods -------------------
    public Recharge createRecharge(Recharge recharge) {
        Member member = memberRepository.findById(recharge.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + recharge.getMemberId()));

        double newBalance = member.getBalance() + recharge.getAmount();
        if (newBalance > 10000) {
            throw new RuntimeException("Balance cannot exceed 10000");
        }

        member.setBalance(newBalance);
        memberRepository.save(member);

        return rechargeRepository.save(recharge);
    }

    public List<Recharge> getAllRecharges() {
        return rechargeRepository.findAll();
    }

    public Recharge getRechargeById(String id) {
        return rechargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recharge not found with id: " + id));
    }

    // ------------------- Game Methods -------------------
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(String id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    /**
     * Register a member for a game:
     *  - checks the member & game exist
     *  - checks if already registered
     *  - checks member balance >= entry fee (game.price)
     *  - deducts the entry fee from member.balance
     *  - saves both Member and Game
     *
     * NOTE: This method is annotated with @Transactional to attempt atomicity; ensure your MongoDB supports transactions
     * (replica set). If not, you can remove @Transactional and accept a best-effort (two saves) approach.
     */
    @Transactional
    public String registerMemberForGame(String memberId, String gameId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + gameId));

        if (game.isMemberRegistered(memberId)) {
            throw new RuntimeException("Member already registered for this game");
        }

        double fee = game.getPrice();
        if (member.getBalance() < fee) {
            throw new RuntimeException("Insufficient balance to register for game: " + game.getName());
        }

        // deduct fee and save
        member.setBalance(member.getBalance() - fee);
        game.addRegisteredMemberId(memberId);

        memberRepository.save(member);
        gameRepository.save(game);

        return "Member " + member.getName() + " registered for game: " + game.getName() + ". Fee deducted: " + fee;
    }
}
