package com.efootball.tournament.dto.request;

import com.efootball.tournament.enums.TournamnetStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTournamentRequest {
    private String name;
    private String season;
    private LocalDate startDate;
    private LocalDate endDate;
    private TournamnetStatus status;
}
