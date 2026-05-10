package com.efootball.tournament.entity;

import com.efootball.tournament.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="match_score_approval")
@Getter
@Setter
@NoArgsConstructor
public class MatchScoreApproval extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer homeScore;
    private Integer awayScore;

    @ManyToOne(optional = false)
    @JoinColumn(name = "submitted_by")
    private User submittedBy;

    @OneToOne(optional = false)
    @JoinColumn(name = "match_id")
    private Match match;


    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;;

    private String rejectionReason;

    private LocalDateTime submittedAt;
    private LocalDateTime approvedAt;
}
