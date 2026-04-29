package com.efootball.tournament.dto;

import java.time.LocalDate;

public class MatchResponse {

    private Long id;
    private String tournamentName;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDate matchDate;

    public MatchResponse(Long id, String tournamentName,
                         String homeTeam, String awayTeam,
                         Integer homeScore, Integer awayScore,
                         LocalDate matchDate) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.matchDate = matchDate;
    }

    public Long getId() { return id; }
    public String getTournamentName() { return tournamentName; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public Integer getHomeScore() { return homeScore; }
    public Integer getAwayScore() { return awayScore; }
    public LocalDate getMatchDate() { return matchDate; }
}
