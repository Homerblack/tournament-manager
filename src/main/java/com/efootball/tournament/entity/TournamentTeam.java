package com.efootball.tournament.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "tournamnet_teams",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tournament_id", "team_id"})
)
public class TournamentTeam extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() { return id; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
