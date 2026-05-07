package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.request.CreateMatchRequest;
import com.efootball.tournament.dto.request.UpdateMatchRequest;
import com.efootball.tournament.dto.response.MatchResponse;
import com.efootball.tournament.dto.response.StandingResponse;
import com.efootball.tournament.entity.Match;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.entity.Tournament;
import com.efootball.tournament.repository.MatchRepository;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.repository.TournamentRepository;
import com.efootball.tournament.repository.TournamentTeamRepository;
import com.efootball.tournament.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final TournamentTeamRepository tournamentTeamRepository;

    @Override
    public MatchResponse create(CreateMatchRequest request) {

        Tournament tournament = tournamentRepository.findById(
                request.getTournamentId()
        ).orElseThrow(() -> new RuntimeException("Tournament not found."));

        Team homeTeam = teamRepository.findById(
                request.getHomeTeamId()
        ).orElseThrow(() -> new RuntimeException("Home team not found."));

        Team awayTeam = teamRepository.findById(
                request.getAwayTeamId()
        ).orElseThrow(() -> new RuntimeException("Away team not found."));

        if (homeTeam.getId().equals(awayTeam.getId())) {
            throw new RuntimeException("Teams must be different.");
        }

        boolean homeExists =
                tournamentTeamRepository
                        .existsByTournamentIdAndTeamId(
                                tournament.getId(),
                                homeTeam.getId()
                        );

        boolean awayExists =
                tournamentTeamRepository
                        .existsByTournamentIdAndTeamId(
                                tournament.getId(),
                                awayTeam.getId()
                        );

        if (!homeExists || !awayExists) {
            throw new RuntimeException("Both teams must belong to tournament.");
        }

        Match match = new Match();
        match.setTournament(tournament);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setMatchDate(request.getMatchDate());

        Match saved = matchRepository.save(match);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchResponse> getByTournament(Long tournamentId) {

        return matchRepository.findByTournamentId(tournamentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchResponse> getAllMatches() {

        return matchRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MatchResponse getById(Long id) {

        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found."));

        return mapToResponse(match);
    }

    @Override
    public MatchResponse update(Long id, UpdateMatchRequest request) {

        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found."));

        if (request.getMatchDate() != null) {
            match.setMatchDate(request.getMatchDate());
        }

        Match updated = matchRepository.save(match);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found."));

        matchRepository.delete(match);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StandingResponse> getStandings(Long tournamentId) {

        List<Match> matches =
                matchRepository.findByTournamentId(tournamentId);

        Map<Long, StandingResponse> table = new HashMap<>();

        for (Match match : matches) {

            if (match.getHomeScore() == null ||
                    match.getAwayScore() == null) {
                continue;
            }

            StandingResponse home =
                    table.computeIfAbsent(
                            match.getHomeTeam().getId(),
                            id -> createStanding(
                                    match.getHomeTeam().getName()
                            )
                    );

            StandingResponse away =
                    table.computeIfAbsent(
                            match.getAwayTeam().getId(),
                            id -> createStanding(
                                    match.getAwayTeam().getName()
                            )
                    );

            updatePlayed(home, away);

            int hs = match.getHomeScore();
            int as = match.getAwayScore();

            home.setGoalsFor(home.getGoalsFor() + hs);
            home.setGoalsAgainst(home.getGoalsAgainst() + as);

            away.setGoalsFor(away.getGoalsFor() + as);
            away.setGoalsAgainst(away.getGoalsAgainst() + hs);

            if (hs > as) {
                home.setWins(home.getWins() + 1);
                away.setLosses(away.getLosses() + 1);
                home.setPoints(home.getPoints() + 3);

            } else if (hs < as) {
                away.setWins(away.getWins() + 1);
                home.setLosses(home.getLosses() + 1);
                away.setPoints(away.getPoints() + 3);

            } else {
                home.setDraws(home.getDraws() + 1);
                away.setDraws(away.getDraws() + 1);

                home.setPoints(home.getPoints() + 1);
                away.setPoints(away.getPoints() + 1);
            }
        }

        List<StandingResponse> standings =
                new ArrayList<>(table.values());

        standings.forEach(row ->
                row.setGoalDifference(
                        row.getGoalsFor() -
                                row.getGoalsAgainst()
                )
        );

        standings.sort(
                Comparator.comparing(
                                StandingResponse::getPoints
                        ).reversed()
                        .thenComparing(
                                StandingResponse::getGoalDifference
                        ).reversed()
                        .thenComparing(
                                StandingResponse::getGoalsFor
                        ).reversed()
        );

        for (int i = 0; i < standings.size(); i++) {
            standings.get(i).setPosition(i + 1);
        }

        return standings;
    }

    private StandingResponse createStanding(String teamName) {

        StandingResponse row = new StandingResponse();

        row.setTeam(teamName);
        row.setPlayed(0);
        row.setWins(0);
        row.setDraws(0);
        row.setLosses(0);
        row.setGoalsFor(0);
        row.setGoalsAgainst(0);
        row.setGoalDifference(0);
        row.setPoints(0);

        return row;
    }

    private void updatePlayed(
            StandingResponse home,
            StandingResponse away
    ) {
        home.setPlayed(home.getPlayed() + 1);
        away.setPlayed(away.getPlayed() + 1);
    }

    private MatchResponse mapToResponse(Match match) {

        MatchResponse response = new MatchResponse();

        response.setId(match.getId());
        response.setTournamentName(
                match.getTournament().getName()
        );
        response.setHomeTeam(
                match.getHomeTeam().getName()
        );
        response.setAwayTeam(
                match.getAwayTeam().getName()
        );
        response.setHomeScore(match.getHomeScore());
        response.setAwayScore(match.getAwayScore());
        response.setMatchDate(match.getMatchDate());

        return response;
    }
}