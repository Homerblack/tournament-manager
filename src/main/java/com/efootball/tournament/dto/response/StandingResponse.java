package com.efootball.tournament.dto.response;


import lombok.Data;

@Data
public class StandingResponse {

    private Integer position;
    private String team;
    private Integer played;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;
    private Integer points;
    }


