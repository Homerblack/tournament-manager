package com.efootball.tournament.repository;

import com.efootball.tournament.entity.MatchScoreApproval;
import com.efootball.tournament.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchScoreApprovalRepository extends JpaRepository<MatchScoreApproval, Long> {
    Optional<MatchScoreApproval> findByMatchId(Long matchId);

    Optional<MatchScoreApproval> findByMatchIdAndStatus(
            Long matchId,
            ApprovalStatus status
    );

    List<MatchScoreApproval> findByStatus(ApprovalStatus status);

    List<MatchScoreApproval> findByApprovedById(Long userId);

    List<MatchScoreApproval> findBySubmittedById(Long userId);
    long countByStatus(ApprovalStatus status);
}
