package com.efootball.tournament.service;

import com.efootball.tournament.dto.request.AdminUserRequest;
import com.efootball.tournament.dto.request.ChangePasswordRequest;
import com.efootball.tournament.dto.request.LoginRequest;
import com.efootball.tournament.dto.response.AdminUserResponse;
import com.efootball.tournament.dto.response.LoginResponse;

public interface UserService {

    AdminUserResponse create(AdminUserRequest request);

    LoginResponse login(LoginRequest request);

    AdminUserResponse getByAdminId(String adminID);

    void changePassword(Long userId, ChangePasswordRequest request);

    void disableUser(Long userId);

}
