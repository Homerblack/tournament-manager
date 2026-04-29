package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateTournamentRequest;
import com.efootball.tournament.dto.TournamentResponse;
import com.efootball.tournament.dto.TournamentTeamResponse;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.entity.Tournament;
import com.efootball.tournament.entity.TournamentTeam;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.repository.TournamentRepository;
import com.efootball.tournament.repository.TournamentTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final TeamRepository teamRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentTeamRepository tournamentTeamRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public TournamentResponse create(CreateTournamentRequest request) {

        Tournament tournament = new Tournament();
        tournament.setName(request.getName());
        tournament.setSeason(request.getSeason());
        tournament.setStartDate(request.getStartDate());
        tournament.setEndDate(request.getEndDate());

        Tournament saved = tournamentRepository.save(tournament);

        return new TournamentResponse(
                saved.getId(),
                saved.getName(),
                saved.getSeason(),
                saved.getStartDate(),
                saved.getEndDate()
        );
    }

    @Override
    public List<TournamentResponse> getAll() {
        return tournamentRepository.findAll()
                .stream()
                .map(t -> new TournamentResponse(
                        t.getId(),
                        t.getName(),
                        t.getSeason(),
                        t.getStartDate(),
                        t.getEndDate()
                ))
                .toList();
    }

    @Override
    public void addTeam(Long tournamentId, Long teamId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        tournamentTeamRepository
                .findByTournamentIdAndTeamId(tournamentId, teamId)
                .ifPresent(tt -> {
                    throw new RuntimeException("Team already added");
                });

        TournamentTeam tt = new TournamentTeam();
        tt.setTournament(tournament);
        tt.setTeam(team);

        tournamentTeamRepository.save(tt);
    }

    @Override
    public List<TournamentTeamResponse> getTeams(Long tournamentId) {

        return tournamentTeamRepository.findByTournamentId(tournamentId)
                .stream()
                .map(tt -> new TournamentTeamResponse(
                        tt.getTeam().getId(),
                        tt.getTeam().getName(),
                        tt.getTeam().getShortName()
                ))
                .toList();
    }

    @Override
    public void removeTeam(Long tournamentId, Long teamId) {
        tournamentTeamRepository.deleteByTournamentIdAndTeamId(tournamentId, teamId);
    }
}
