package com.efootball.tournament.dto;

import java.time.LocalDate;

public class TournamentResponse {

    private Long id;
    private String name;
    private String season;
    private LocalDate startDate;
    private LocalDate endDate;

    public TournamentResponse(Long id, String name, String season,
                              LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSeason() { return season; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
