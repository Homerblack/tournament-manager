package com.efootball.tournament.controller;

import com.efootball.tournament.dto.CreateTournamentRequest;
import com.efootball.tournament.dto.TournamentResponse;
import com.efootball.tournament.dto.TournamentTeamResponse;
import com.efootball.tournament.service.TournamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    public TournamentResponse create(@RequestBody CreateTournamentRequest request) {
        return tournamentService.create(request);
    }

    @GetMapping
    public List<TournamentResponse> getAll() {
        return tournamentService.getAll();
    }
    @PostMapping("/{tournamentId}/teams/{teamId}")
    public void addTeam(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId
    ) {
        tournamentService.addTeam(tournamentId, teamId);
    }

    @GetMapping("/{tournamentId}/teams")
    public List<TournamentTeamResponse> getTeams(
            @PathVariable Long tournamentId
    ) {
        return tournamentService.getTeams(tournamentId);
    }

    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public void removeTeam(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId
    ) {
        tournamentService.removeTeam(tournamentId, teamId);
    }

}
