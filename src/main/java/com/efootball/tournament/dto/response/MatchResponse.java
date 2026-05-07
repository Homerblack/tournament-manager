package com.efootball.tournament.dto.response;

import com.efootball.tournament.enums.MatchStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MatchResponse {


    private Long id;
    private String tournamentName;
    private String homeTeam;
    private String awayTeam;
    private LocalDate matchDate;
    private MatchStatus status;

    private Integer homeScore;
    private Integer awayScore;
}
