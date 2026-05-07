package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.response.DashboardResponse;
import com.efootball.tournament.enums.ApprovalStatus;
import com.efootball.tournament.repository.MatchRepository;
import com.efootball.tournament.repository.MatchScoreApprovalRepository;
import com.efootball.tournament.repository.TeamRepository;
import com.efootball.tournament.repository.TournamentRepository;
import com.efootball.tournament.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final MatchScoreApprovalRepository approvalRepository;

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalTeams(teamRepository.count());

        response.setTotalTournaments(tournamentRepository.count());

        response.setTotalMatches(matchRepository.count());

        response.setPendingApprovals(approvalRepository.countByStatus(ApprovalStatus.PENDING));

        response.setActiveTournaments(tournamentRepository.countByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate.now(), LocalDate.now()));

        response.setUpcomingMatches(matchRepository.countByMatchDateAfter(LocalDate.now()));

        return response;
    }
}