package com.efootball.tournament.repository;

import com.efootball.tournament.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository  extends JpaRepository<Match, Long> {
}
