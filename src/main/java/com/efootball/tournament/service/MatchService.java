package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateMatchRequest;
import com.efootball.tournament.dto.MatchResponse;
import com.efootball.tournament.dto.StandingResponse;

import java.util.List;

public interface MatchService {
    MatchResponse create(CreateMatchRequest request);

    List<MatchResponse> getByTournament(Long tournamentId);

    List<StandingResponse> getStandings(Long tournamentId);

    List<MatchResponse> getAllMatches();
}
