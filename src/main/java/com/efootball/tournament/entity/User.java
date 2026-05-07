package com.efootball.tournament.entity;
import com.efootball.tournament.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false, unique=true)
    private String adminID;

    @Column(nullable=false)
    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    private LocalDate startDate;

    private LocalDate endDate;

    @PrePersist
    public void prePersist() {

        super.prePersist();

        if(startDate == null)
            startDate = LocalDate.now();

        if(endDate == null)
            endDate = startDate.plusYears(1);
    }
}

