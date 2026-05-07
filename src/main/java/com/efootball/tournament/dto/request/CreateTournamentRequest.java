package com.efootball.tournament.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTournamentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String season;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
