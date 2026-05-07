package com.efootball.tournament.repository;

import com.efootball.tournament.entity.Tournament;
import com.efootball.tournament.enums.TournamnetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TournamentRepository  extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findByNameAndSeason(String name, String season);

    boolean existsByNameAndSeason(String name, String season);

    List<Tournament> findByNameContainingIgnoreCase(String name);

    List<Tournament> findBySeason(String season);

    List<Tournament> findByStatus(TournamnetStatus status);
    long countByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate today1,
            LocalDate today2
    );
}
