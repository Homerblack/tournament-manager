package com.efootball.tournament.dto.response;

import com.efootball.tournament.enums.TournamnetStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TournamentResponse {

    private Long id;
    private String name;
    private String season;
    private LocalDate startDate;
    private LocalDate endDate;
    private TournamnetStatus status;
}
