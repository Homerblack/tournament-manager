package com.efootball.tournament.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTeamRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 5)
    private String shortName;
}
