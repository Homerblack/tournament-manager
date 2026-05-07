package com.efootball.tournament.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateMatchRequest {
    private LocalDate matchDate;
}
