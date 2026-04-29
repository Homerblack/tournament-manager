package com.efootball.tournament.dto;

public class TeamResponse {

    private Long id;
    private String name;
    private String shortName;

    public TeamResponse(Long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
