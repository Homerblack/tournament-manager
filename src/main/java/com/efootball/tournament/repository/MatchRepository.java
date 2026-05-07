package com.efootball.tournament.repository;

import com.efootball.tournament.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository  extends JpaRepository<Match, Long> {

    List<Match> findByTournamentId(Long tournamentId);

    List<Match> findByHomeTeamIdOrAwayTeamId(Long homeId, Long awayId);

    boolean existsByTournamentIdAndHomeTeamIdAndAwayTeamId(
            Long tournamentId,
            Long homeTeamId,
            Long awayTeamId
    );
    long countByMatchDateAfter(LocalDate date);
}
