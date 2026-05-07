package com.efootball.tournament.service;

import com.efootball.tournament.dto.request.RejectScoreRequest;
import com.efootball.tournament.dto.request.SubmitScoreRequest;
import com.efootball.tournament.dto.response.PendingApprovalResponse;

import java.util.List;

public interface MatchScoreApprovalService {

    void submitScore(Long matchId, Long userId, SubmitScoreRequest request);

    void approveScore(Long matchId , Long userId);

    void rejectScore(Long matchId,Long userId, RejectScoreRequest request);

    List<PendingApprovalResponse> getPendingApprovals();
}
