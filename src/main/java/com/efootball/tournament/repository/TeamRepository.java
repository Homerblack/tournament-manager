package com.efootball.tournament.repository;

import com.efootball.tournament.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);

    Optional<Team> findByShortName(String shortName);

    boolean existsByName(String name);

    boolean existsByShortName(String shortName);

}