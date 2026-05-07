package com.efootball.tournament.service;

import com.efootball.tournament.dto.request.CreateMatchRequest;
import com.efootball.tournament.dto.request.UpdateMatchRequest;
import com.efootball.tournament.dto.response.MatchResponse;
import com.efootball.tournament.dto.response.StandingResponse;

import java.util.List;

public interface MatchService {
    MatchResponse create(CreateMatchRequest request);

    MatchResponse update(Long id, UpdateMatchRequest request);

    void delete(Long id);

    MatchResponse getById(Long id);

    List<MatchResponse> getByTournament(Long tournamentId);

    List<MatchResponse> getAllMatches();

    List<StandingResponse> getStandings(Long tournamentId);
}
