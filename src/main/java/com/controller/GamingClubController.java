package com.example.gamingclub.controller;

import com.example.gamingclub.model.Member;
import com.example.gamingclub.model.Recharge;
import com.example.gamingclub.model.Game;
import com.example.gamingclub.service.GamingClubService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GamingClubController {

    private final GamingClubService service;

    public GamingClubController(GamingClubService service) {
        this.service = service;
    }

    // Members
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
        return ResponseEntity.ok(service.createMember(member));
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(service.getAllMembers());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        return ResponseEntity.ok(service.getMemberById(id));
    }

    // Recharges
    @PostMapping("/recharges")
    public ResponseEntity<Recharge> createRecharge(@Valid @RequestBody Recharge recharge) {
        return ResponseEntity.ok(service.createRecharge(recharge));
    }

    @GetMapping("/recharges")
    public ResponseEntity<List<Recharge>> getAllRecharges() {
        return ResponseEntity.ok(service.getAllRecharges());
    }

    @GetMapping("/recharges/{id}")
    public ResponseEntity<Recharge> getRechargeById(@PathVariable String id) {
        return ResponseEntity.ok(service.getRechargeById(id));
    }

    // Games
    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game) {
        return ResponseEntity.ok(service.createGame(game));
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(service.getAllGames());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return ResponseEntity.ok(service.getGameById(id));
    }

    // Register member for game (deducts entry fee from member.balance)
    @PostMapping("/games/{gameId}/register/{memberId}")
    public ResponseEntity<String> registerForGame(@PathVariable String memberId, @PathVariable String gameId) {
        return ResponseEntity.ok(service.registerMemberForGame(memberId, gameId));
    }
}
