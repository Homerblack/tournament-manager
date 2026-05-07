package com.efootball.tournament.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmitScoreRequest {
    @NotNull
    @Min(0)
    private Integer homeScore;

    @NotNull
    @Min(0)
    private Integer awayScore;
}

