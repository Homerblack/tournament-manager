package com.efootball.tournament.service;

import com.efootball.tournament.dto.CreateMatchRequest;
import com.efootball.tournament.dto.MatchResponse;
import com.efootball.tournament.dto.StandingResponse;
import com.efootball.tournament.entity.Match;
import com.efootball.tournament.entity.Team;
import com.efootball.tournament.entity.Tournament;
import com.efootball.tournament.repository.MatchRepository;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    public MatchServiceImpl(
            MatchRepository matchRepository,
            TournamentRepository tournamentRepository,
            TeamRepository teamRepository
    ) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public MatchResponse create(CreateMatchRequest request) {

        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Team home = teamRepository.findById(request.getHomeTeamId())
                .orElseThrow(() -> new RuntimeException("Home team not found"));

        Team away = teamRepository.findById(request.getAwayTeamId())
                .orElseThrow(() -> new RuntimeException("Away team not found"));

        Match match = new Match();
        match.setTournament(tournament);
        match.setHomeTeam(home);
        match.setAwayTeam(away);
        match.setHomeScore(request.getHomeScore());
        match.setAwayScore(request.getAwayScore());
        match.setMatchDate(request.getMatchDate());

        Match saved = matchRepository.save(match);

        return new MatchResponse(
                saved.getId(),
                tournament.getName(),
                home.getName(),
                away.getName(),
                saved.getHomeScore(),
                saved.getAwayScore(),
                saved.getMatchDate()
        );
    }

    @Override
    public List<MatchResponse> getByTournament(Long tournamentId) {

        return matchRepository.findAll()
                .stream()
                .filter(m -> m.getTournament().getId().equals(tournamentId))
                .map(m -> new MatchResponse(
                        m.getId(),
                        m.getTournament().getName(),
                        m.getHomeTeam().getName(),
                        m.getAwayTeam().getName(),
                        m.getHomeScore(),
                        m.getAwayScore(),
                        m.getMatchDate()
                ))
                .toList();
    }

    @Override
    public List<StandingResponse> getStandings(Long tournamentId) {

        Map<String, int[]> table = new HashMap<>();

        List<Match> matches = matchRepository.findAll()
                .stream()
                .filter(m -> m.getTournament().getId().equals(tournamentId))
                .toList();

        for (Match m : matches) {

            String home = m.getHomeTeam().getName();
            String away = m.getAwayTeam().getName();

            table.putIfAbsent(home, new int[8]);
            table.putIfAbsent(away, new int[8]);

            int[] h = table.get(home);
            int[] a = table.get(away);

            int hs = m.getHomeScore();
            int as = m.getAwayScore();

            h[0]++;
            a[0]++;

            h[4] += hs;
            h[5] += as;

            a[4] += as;
            a[5] += hs;

            if (hs > as) {
                h[1]++;
                a[3]++;
                h[7] += 3;
            } else if (hs < as) {
                a[1]++;
                h[3]++;
                a[7] += 3;
            } else {
                h[2]++;
                a[2]++;
                h[7]++;
                a[7]++;
            }
        }

        return table.entrySet()
                .stream()
                .map(e -> {
                    int[] s = e.getValue();
                    return new StandingResponse(
                            e.getKey(),
                            s[0],
                            s[1],
                            s[2],
                            s[3],
                            s[4],
                            s[5],
                            s[4] - s[5],
                            s[7]
                    );
                })
                .sorted(Comparator
                        .comparingInt(StandingResponse::getPoints).reversed()
                        .thenComparingInt(StandingResponse::getGoalDifference).reversed()
                        .thenComparingInt(StandingResponse::getGoalsFor).reversed())
                .toList();
    }

    @Override
    public List<MatchResponse> getAllMatches() {

        return matchRepository.findAll()
                .stream()
                .map(m -> new MatchResponse(
                        m.getId(),
                        m.getTournament().getName(),
                        m.getHomeTeam().getName(),
                        m.getAwayTeam().getName(),
                        m.getHomeScore(),
                        m.getAwayScore(),
                        m.getMatchDate()
                ))
                .toList();
    }
}
