package com.efootball.tournament.dto.response;

import lombok.Data;

@Data
public class DashboardResponse {

    private Long totalTeams;

    private Long totalTournaments;

    private Long totalMatches;

    private Long pendingApprovals;

    private Long activeTournaments;

    private Long upcomingMatches;

}
