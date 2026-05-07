package com.efootball.tournament.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RejectScoreRequest {
    @NotBlank
    private String reason;
}
