package com.efootball.tournament.repository;

import com.efootball.tournament.entity.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {

    List<TournamentTeam> findByTournamentId(Long tournamentId);

    List<TournamentTeam> findByTeamId(Long teamId);

    Optional<TournamentTeam> findByTournamentIdAndTeamId(Long tournamentId, Long teamId);

    boolean existsByTournamentIdAndTeamId(Long tournamentId, Long teamId);

    void deleteByTournamentIdAndTeamId(Long tournamentId, Long teamId);
}
