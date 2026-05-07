package com.efootball.tournament.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMatchRequest {

    @NotNull
    private Long tournamentId;

    @NotNull
    private Long homeTeamId;

    @NotNull
    private Long awayTeamId;

    @NotNull
    private LocalDate matchDate;

}
