package com.efootball.tournament.dto.request;

import lombok.Data;

@Data
public class MatchScoreRequest {

    String userId;
    String teamId;
    String teamName;
    String  matchId;

}
