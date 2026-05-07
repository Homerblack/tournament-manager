package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.request.CreateTeamRequest;
import com.efootball.tournament.dto.request.UpdateTeamRequest;
import com.efootball.tournament.dto.response.TeamResponse;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public TeamResponse create(CreateTeamRequest request) {

        if (teamRepository.existsByName(request.getName())) {
            throw new RuntimeException("Team name already exists.");
        }

        if (teamRepository.existsByShortName(request.getShortName())) {
            throw new RuntimeException("Short name already exists.");
        }

        Team team = new Team();
        team.setName(request.getName().trim());
        team.setShortName(request.getShortName().trim().toUpperCase());

        Team saved = teamRepository.save(team);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponse> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponse getById(Long id) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found."));

        return mapToResponse(team);
    }

    @Override
    public TeamResponse update(Long id, UpdateTeamRequest request) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found."));

        String newName = request.getName().trim();
        String newShortName = request.getShortName().trim().toUpperCase();

        teamRepository.findByName(newName)
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new RuntimeException("Team name already exists.");
                    }
                });

        teamRepository.findByShortName(newShortName)
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new RuntimeException("Short name already exists.");
                    }
                });

        team.setName(newName);
        team.setShortName(newShortName);

        Team updated = teamRepository.save(team);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found."));

        teamRepository.delete(team);
    }

    private TeamResponse mapToResponse(Team team) {

        TeamResponse response = new TeamResponse();

        response.setId(team.getId());
        response.setName(team.getName());
        response.setShortName(team.getShortName());

        return response;
    }
}