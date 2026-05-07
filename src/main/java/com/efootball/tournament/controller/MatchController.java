package com.efootball.tournament.controller;

import com.efootball.tournament.dto.request.*;
import com.efootball.tournament.dto.response.*;
import com.efootball.tournament.service.MatchService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    public MatchResponse create(
            @Valid @RequestBody CreateMatchRequest request) {
        return matchService.create(request);
    }

    @GetMapping
    public List<MatchResponse> getAll() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchResponse getById(@PathVariable Long id) {
        return matchService.getById(id);
    }

    @PutMapping("/{id}")
    public MatchResponse update(
            @PathVariable Long id,
            @RequestBody UpdateMatchRequest request) {
        return matchService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ApiErrorResponse delete(@PathVariable Long id) {
        matchService.delete(id);
        return new ApiErrorResponse("Match deleted successfully.");
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<MatchResponse> getByTournament(
            @PathVariable Long tournamentId) {
        return matchService.getByTournament(tournamentId);
    }

    @GetMapping("/tournament/{tournamentId}/standings")
    public List<StandingResponse> standings(
            @PathVariable Long tournamentId) {
        return matchService.getStandings(tournamentId);
    }
}