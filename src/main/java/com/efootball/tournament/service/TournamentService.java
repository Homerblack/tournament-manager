package com.efootball.tournament.service;

import com.efootball.tournament.dto.request.CreateTournamentRequest;
import com.efootball.tournament.dto.request.UpdateTournamentRequest;
import com.efootball.tournament.dto.response.TournamentResponse;
import com.efootball.tournament.dto.response.TournamentTeamResponse;

import java.util.List;

public interface TournamentService {


    TournamentResponse create(CreateTournamentRequest request);

    TournamentResponse update(Long id, UpdateTournamentRequest request);

    void delete(Long id);

    TournamentResponse getById(Long id);

    List<TournamentResponse> getAll();

    void addTeam(Long tournamentId, Long teamId);

    void removeTeam(Long tournamentId, Long teamId);

    List<TournamentTeamResponse> getTeams(Long tournamentId);
}
