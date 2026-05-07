package com.efootball.tournament.service;

import com.efootball.tournament.dto.request.CreateTeamRequest;
import com.efootball.tournament.dto.request.UpdateTeamRequest;
import com.efootball.tournament.dto.response.TeamResponse;

import java.util.List;

public interface TeamService {
    TeamResponse create(CreateTeamRequest request);

    TeamResponse update(Long id, UpdateTeamRequest request);

    void delete(Long id);

    TeamResponse getById(Long id);

    List<TeamResponse> getAll();
}
