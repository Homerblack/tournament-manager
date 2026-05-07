package com.efootball.tournament.entity;

import jakarta.persistence.*;
import com.efootball.tournament.enums.TournamnetStatus;
import java.time.LocalDate;

@Entity
@Table(name = "tournaments")
public class Tournament extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String season;

    @Enumerated(EnumType.STRING)
    private TournamnetStatus status = TournamnetStatus.UPCOMING;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
