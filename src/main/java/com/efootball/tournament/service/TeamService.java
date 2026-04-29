package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateTeamRequest;
import com.efootball.tournament.dto.TeamResponse;

import java.util.List;

public interface TeamService {
    TeamResponse createTeam(CreateTeamRequest request);

    List<TeamResponse> getAllTeams();
}
