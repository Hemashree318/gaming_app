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

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = service.getAllMembers();
        return ResponseEntity.ok(members);
    }

    
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
        return ResponseEntity.ok(service.addMember(member));
    }

    @PostMapping("/members/{memberId}/recharge")
    public ResponseEntity<Recharge> recharge(@PathVariable String memberId, @RequestParam double amount) {
        return ResponseEntity.ok(service.rechargeMember(memberId, amount));
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(service.getAllGames());
    }

    @PostMapping("/games")
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) {
        return ResponseEntity.ok(service.addGame(game));
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable String memberId,@RequestBody Member updatedFields) {
        return ResponseEntity.ok(service.updateMember(memberId, updatedFields));
    }


}
