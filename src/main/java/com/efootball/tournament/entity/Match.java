package com.efootball.tournament.entity;

import com.efootball.tournament.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "matches")
@Data
public class Match extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Tournament tournament;

    @ManyToOne(optional=false)
    @JoinColumn(name="home_team_id")
    private Team homeTeam;

    @ManyToOne(optional=false)
    @JoinColumn(name="away_team_id")
    private Team awayTeam;


    @Column(name="home_team_name")
    private String homeTeamName;


    @Column(name="away_team_name")
    private String awayTeamName;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.SCHEDULED;

    private Integer homeScore;

    private Integer awayScore;

    private LocalDate matchDate;

}
