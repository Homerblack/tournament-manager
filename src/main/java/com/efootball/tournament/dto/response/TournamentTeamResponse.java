package com.efootball.tournament.dto.response;

import lombok.Data;

@Data
public class TournamentTeamResponse {

    private Long teamId;
    private String teamName;
    private String shortName;
}
