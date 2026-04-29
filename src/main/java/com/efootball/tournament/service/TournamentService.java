package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateTournamentRequest;
import com.efootball.tournament.dto.TournamentResponse;
import com.efootball.tournament.dto.TournamentTeamResponse;

import java.util.List;

public interface TournamentService {
    TournamentResponse create(CreateTournamentRequest request);

    List<TournamentResponse> getAll();

    void addTeam(Long tournamentId, Long teamId);

    List<TournamentTeamResponse> getTeams(Long tournamentId);

    void removeTeam(Long tournamentId, Long teamId);
}
