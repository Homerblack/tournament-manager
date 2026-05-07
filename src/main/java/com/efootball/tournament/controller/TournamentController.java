package com.efootball.tournament.controller;

import com.efootball.tournament.dto.request.*;
import com.efootball.tournament.dto.response.*;
import com.efootball.tournament.service.TournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public TournamentResponse create(
            @Valid @RequestBody CreateTournamentRequest request) {
        return tournamentService.create(request);
    }

    @GetMapping
    public List<TournamentResponse> getAll() {
        return tournamentService.getAll();
    }

    @GetMapping("/{id}")
    public TournamentResponse getById(@PathVariable Long id) {
        return tournamentService.getById(id);
    }

    @PutMapping("/{id}")
    public TournamentResponse update(
            @PathVariable Long id,
            @RequestBody UpdateTournamentRequest request) {
        return tournamentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ApiErrorResponse delete(@PathVariable Long id) {
        tournamentService.delete(id);
        return new ApiErrorResponse("Tournament deleted successfully.");
    }

    @PostMapping("/{tournamentId}/teams/{teamId}")
    public ApiErrorResponse addTeam(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId) {

        tournamentService.addTeam(tournamentId, teamId);
        return new ApiErrorResponse("Team added to tournament.");
    }

    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public ApiErrorResponse removeTeam(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId) {

        tournamentService.removeTeam(tournamentId, teamId);
        return new ApiErrorResponse("Team removed from tournament.");
    }

    @GetMapping("/{tournamentId}/teams")
    public List<TournamentTeamResponse> getTeams(
            @PathVariable Long tournamentId) {
        return tournamentService.getTeams(tournamentId);
    }
}