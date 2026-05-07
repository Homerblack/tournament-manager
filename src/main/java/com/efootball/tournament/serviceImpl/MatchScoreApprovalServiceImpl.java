package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.request.RejectScoreRequest;
import com.efootball.tournament.dto.request.SubmitScoreRequest;
import com.efootball.tournament.dto.response.PendingApprovalResponse;
import com.efootball.tournament.entity.Match;
import com.efootball.tournament.entity.MatchScoreApproval;
import com.efootball.tournament.entity.User;
import com.efootball.tournament.enums.ApprovalStatus;
import com.efootball.tournament.repository.MatchRepository;
import com.efootball.tournament.repository.MatchScoreApprovalRepository;
import com.efootball.tournament.repository.UserRepository;
import com.efootball.tournament.service.MatchScoreApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchScoreApprovalServiceImpl implements MatchScoreApprovalService {

    private final MatchRepository matchRepository;
    private final MatchScoreApprovalRepository approvalRepository;
    private final UserRepository userRepository;

    @Override
    public void submitScore(
            Long matchId,
            Long userId,
            SubmitScoreRequest request
    ) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() ->
                        new RuntimeException("Match not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        approvalRepository.findByMatchIdAndStatus(
                matchId,
                ApprovalStatus.PENDING
        ).ifPresent(existing -> {
            throw new RuntimeException(
                    "Pending approval already exists.");
        });

        MatchScoreApproval approval =
                new MatchScoreApproval();

        approval.setMatch(match);
        approval.setHomeScore(request.getHomeScore());
        approval.setAwayScore(request.getAwayScore());
        approval.setSubmittedBy(user);
        approval.setStatus(ApprovalStatus.PENDING);
        approval.setSubmittedAt(LocalDateTime.now());

        approvalRepository.save(approval);
    }

    @Override
    public void approveScore(
            Long matchId,
            Long userId
    ) {

        MatchScoreApproval approval =
                approvalRepository
                        .findByMatchIdAndStatus(
                                matchId,
                                ApprovalStatus.PENDING
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pending score not found."));

        if (approval.getSubmittedBy()
                .getId()
                .equals(userId)) {

            throw new RuntimeException(
                    "Submitter cannot approve own score.");
        }

        User approver = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        Match match = approval.getMatch();

        match.setHomeScore(approval.getHomeScore());
        match.setAwayScore(approval.getAwayScore());

        matchRepository.save(match);

        approval.setApprovedBy(approver);
        approval.setApprovedAt(LocalDateTime.now());
        approval.setStatus(ApprovalStatus.APPROVED);

        approvalRepository.save(approval);
    }

    @Override
    public void rejectScore(
            Long matchId,
            Long userId,
            RejectScoreRequest request
    ) {

        MatchScoreApproval approval =
                approvalRepository
                        .findByMatchIdAndStatus(
                                matchId,
                                ApprovalStatus.PENDING
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pending score not found."));

        if (approval.getSubmittedBy()
                .getId()
                .equals(userId)) {

            throw new RuntimeException(
                    "Submitter cannot reject own score.");
        }

        User approver = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        approval.setApprovedBy(approver);
        approval.setApprovedAt(LocalDateTime.now());
        approval.setStatus(ApprovalStatus.REJECTED);
        approval.setRejectionReason(
                request.getReason()
        );

        approvalRepository.save(approval);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PendingApprovalResponse>
    getPendingApprovals() {

        return approvalRepository
                .findByStatus(
                        ApprovalStatus.PENDING
                )
                .stream()
                .map(this::mapToPendingResponse)
                .toList();
    }

    private PendingApprovalResponse mapToPendingResponse(
            MatchScoreApproval approval
    ) {

        PendingApprovalResponse response =
                new PendingApprovalResponse();

        response.setMatchId(
                approval.getMatch().getId()
        );

        response.setHomeTeam(
                approval.getMatch()
                        .getHomeTeam()
                        .getName()
        );

        response.setAwayTeam(
                approval.getMatch()
                        .getAwayTeam()
                        .getName()
        );

        response.setHomeScore(
                approval.getHomeScore()
        );

        response.setAwayScore(
                approval.getAwayScore()
        );

        response.setSubmittedBy(
                approval.getSubmittedBy()
                        .getFullName()
        );

        response.setSubmittedAt(
                approval.getSubmittedAt()
        );

        return response;
    }
}