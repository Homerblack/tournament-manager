package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.request.CreateTournamentRequest;
import com.efootball.tournament.dto.request.UpdateTournamentRequest;
import com.efootball.tournament.dto.response.TournamentResponse;
import com.efootball.tournament.dto.response.TournamentTeamResponse;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.entity.Tournament;
import com.efootball.tournament.entity.TournamentTeam;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.repository.TournamentRepository;
import com.efootball.tournament.repository.TournamentTeamRepository;
import com.efootball.tournament.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final TournamentTeamRepository tournamentTeamRepository;

    @Override
    public TournamentResponse create(CreateTournamentRequest request) {

        if (tournamentRepository.existsByNameAndSeason(
                request.getName(),
                request.getSeason())) {

            throw new RuntimeException("Tournament already exists for this season.");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date.");
        }

        Tournament tournament = new Tournament();
        tournament.setName(request.getName().trim());
        tournament.setSeason(request.getSeason().trim());
        tournament.setStartDate(request.getStartDate());
        tournament.setEndDate(request.getEndDate());

        Tournament saved = tournamentRepository.save(tournament);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TournamentResponse> getAll() {
        return tournamentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TournamentResponse getById(Long id) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found."));

        return mapToResponse(tournament);
    }

    @Override
    public TournamentResponse update(Long id, UpdateTournamentRequest request) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found."));

        if (request.getName() != null) {
            tournament.setName(request.getName().trim());
        }

        if (request.getSeason() != null) {
            tournament.setSeason(request.getSeason().trim());
        }

        if (request.getStartDate() != null) {
            tournament.setStartDate(request.getStartDate());
        }

        if (request.getEndDate() != null) {
            tournament.setEndDate(request.getEndDate());
        }

        if (tournament.getEndDate().isBefore(tournament.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date.");
        }

        Tournament updated = tournamentRepository.save(tournament);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found."));

        tournamentRepository.delete(tournament);
    }

    @Override
    public void addTeam(Long tournamentId, Long teamId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found."));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found."));

        if (tournamentTeamRepository.existsByTournamentIdAndTeamId(
                tournamentId, teamId)) {

            throw new RuntimeException("Team already added to tournament.");
        }

        TournamentTeam relation = new TournamentTeam();
        relation.setTournament(tournament);
        relation.setTeam(team);

        tournamentTeamRepository.save(relation);
    }

    @Override
    public void removeTeam(Long tournamentId, Long teamId) {

        if (!tournamentTeamRepository.existsByTournamentIdAndTeamId(
                tournamentId, teamId)) {

            throw new RuntimeException("Team not linked to tournament.");
        }

        tournamentTeamRepository.deleteByTournamentIdAndTeamId(
                tournamentId, teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TournamentTeamResponse> getTeams(Long tournamentId) {

        return tournamentTeamRepository.findByTournamentId(tournamentId)
                .stream()
                .map(this::mapToTournamentTeamResponse)
                .toList();
    }

    private TournamentResponse mapToResponse(Tournament tournament) {

        TournamentResponse response = new TournamentResponse();

        response.setId(tournament.getId());
        response.setName(tournament.getName());
        response.setSeason(tournament.getSeason());
        response.setStartDate(tournament.getStartDate());
        response.setEndDate(tournament.getEndDate());

        return response;
    }

    private TournamentTeamResponse mapToTournamentTeamResponse(
            TournamentTeam relation) {

        TournamentTeamResponse response = new TournamentTeamResponse();

        response.setTeamId(relation.getTeam().getId());
        response.setTeamName(relation.getTeam().getName());
        response.setShortName(relation.getTeam().getShortName());

        return response;
    }
}