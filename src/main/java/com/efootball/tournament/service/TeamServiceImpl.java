package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateTeamRequest;
import com.efootball.tournament.dto.TeamResponse;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResponse createTeam(CreateTeamRequest request) {

        Team team = new Team();
        team.setName(request.getName());
        team.setShortName(request.getShortName());

        Team saved = teamRepository.save(team);

        return new TeamResponse(
                saved.getId(),
                saved.getName(),
                saved.getShortName()
        );
    }

    @Override
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(team -> new TeamResponse(
                        team.getId(),
                        team.getName(),
                        team.getShortName()))
                .toList();
    }
}
