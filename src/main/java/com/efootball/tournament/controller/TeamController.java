package com.efootball.tournament.controller;

import com.efootball.tournament.dto.request.*;
import com.efootball.tournament.dto.response.*;
import com.efootball.tournament.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public TeamResponse create(
            @Valid @RequestBody CreateTeamRequest request) {
        return teamService.create(request);
    }

    @GetMapping
    public List<TeamResponse> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public TeamResponse getById(@PathVariable Long id) {
        return teamService.getById(id);
    }

    @PutMapping("/{id}")
    public TeamResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTeamRequest request) {
        return teamService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ApiErrorResponse delete(@PathVariable Long id) {
        teamService.delete(id);
        return new ApiErrorResponse("Team deleted successfully.");
    }
}