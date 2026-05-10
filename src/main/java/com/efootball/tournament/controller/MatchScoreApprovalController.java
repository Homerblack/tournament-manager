package com.efootball.tournament.controller;

import com.efootball.tournament.dto.request.RejectScoreRequest;
import com.efootball.tournament.dto.request.SubmitScoreRequest;
import com.efootball.tournament.dto.response.PendingApprovalResponse;
import com.efootball.tournament.service.MatchScoreApprovalService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-approvals")
@RequiredArgsConstructor
public class MatchScoreApprovalController {

    private final MatchScoreApprovalService
            approvalService;

    @PostMapping("/{matchId}/submit")
    public ResponseEntity<String> submitScore(

            @PathVariable Long matchId,

            @RequestParam Long userId,

            @Valid
            @RequestBody
            SubmitScoreRequest request
    ) {

        approvalService.submitScore(
                matchId,
                userId,
                request
        );

        return ResponseEntity.ok(
                "Score submitted successfully."
        );
    }

    @PostMapping("/{matchId}/approve")
    public ResponseEntity<String> approveScore(

            @PathVariable Long matchId,

            @RequestParam Long userId
    ) {

        approvalService.approveScore(
                matchId,
                userId
        );

        return ResponseEntity.ok(
                "Score approved successfully."
        );
    }

    @PostMapping("/{matchId}/reject")
    public ResponseEntity<String> rejectScore(

            @PathVariable Long matchId,

            @RequestParam Long userId,

            @Valid
            @RequestBody
            RejectScoreRequest request
    ) {

        approvalService.rejectScore(
                matchId,
                userId,
                request
        );

        return ResponseEntity.ok(
                "Score rejected successfully."
        );
    }

    @GetMapping("/pending")
    public ResponseEntity<
            List<PendingApprovalResponse>
            > getPendingApprovals() {

        return ResponseEntity.ok(
                approvalService
                        .getPendingApprovals()
        );
    }
}