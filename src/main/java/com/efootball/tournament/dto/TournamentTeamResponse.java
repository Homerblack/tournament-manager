package com.efootball.tournament.dto;

public class TournamentTeamResponse {
    private Long teamId;
    private String teamName;
    private String shortName;

    public TournamentTeamResponse(Long teamId, String teamName, String shortName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.shortName = shortName;
    }

    public Long getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
    public String getShortName() { return shortName; }
}
