package com.efootball.tournament.controller;

import com.efootball.tournament.dto.request.*;
import com.efootball.tournament.dto.response.*;
import com.efootball.tournament.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public AdminUserResponse register(
            @Valid @RequestBody AdminUserRequest request) {
        return userService.create(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{adminId}")
    public AdminUserResponse getByAdminId(
            @PathVariable String adminId) {
        return userService.getByAdminId(adminId);
    }

    @PutMapping("/{userId}/password")
    public ApiErrorResponse changePassword(
            @PathVariable Long userId,
            @Valid @RequestBody ChangePasswordRequest request) {

        userService.changePassword(userId, request);
        return new ApiErrorResponse("Password updated successfully.");
    }

    @PutMapping("/{userId}/disable")
    public ApiErrorResponse disableUser(
            @PathVariable Long userId) {

        userService.disableUser(userId);
        return new ApiErrorResponse("User disabled successfully.");
    }
}