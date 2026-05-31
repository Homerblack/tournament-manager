package com.efootball.tournament.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teams")
@Data
public class Team extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false, unique=true)
    private String shortName;

}
