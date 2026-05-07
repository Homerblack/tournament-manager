package com.efootball.tournament.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PendingApprovalResponse {

    private Long matchId;
    private String homeTeam;
    private String awayTeam;

    private Integer homeScore;
    private Integer awayScore;

    private String submittedBy;

    private LocalDateTime submittedAt;
}
