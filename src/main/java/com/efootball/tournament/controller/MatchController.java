package com.efootball.tournament.controller;


import com.efootball.tournament.dto.CreateMatchRequest;
import com.efootball.tournament.dto.MatchResponse;
import com.efootball.tournament.dto.StandingResponse;
import com.efootball.tournament.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/matches")
    public MatchResponse create(@RequestBody CreateMatchRequest request) {
        return matchService.create(request);
    }

    @GetMapping("/tournaments/{id}/matches")
    public List<MatchResponse> getByTournament(@PathVariable Long id) {
        return matchService.getByTournament(id);
    }
    @GetMapping("/tournaments/{id}/standings")
    public List<StandingResponse> getStandings(@PathVariable Long id) {
        return matchService.getStandings(id);
    }

    @GetMapping("/matches/all")
    public List<MatchResponse> getAllMatches() {
        return matchService.getAllMatches();
    }
}
