package com.efootball.tournament.repository;

import com.efootball.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository  extends JpaRepository<Tournament, Long> {
}
