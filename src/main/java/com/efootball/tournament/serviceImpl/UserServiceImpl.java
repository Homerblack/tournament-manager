package com.efootball.tournament.serviceImpl;

import com.efootball.tournament.dto.request.AdminUserRequest;
import com.efootball.tournament.dto.request.ChangePasswordRequest;
import com.efootball.tournament.dto.request.LoginRequest;
import com.efootball.tournament.dto.response.AdminUserResponse;
import com.efootball.tournament.dto.response.LoginResponse;
import com.efootball.tournament.entity.User;
import com.efootball.tournament.repository.UserRepository;
import com.efootball.tournament.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminUserResponse create(AdminUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        if (userRepository.existsByAdminID(request.getAdminID())) {
            throw new RuntimeException("Admin ID already exists.");
        }

        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setUsername(request.getUsername().trim());
        user.setAdminID(request.getAdminID().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        return mapToAdminResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password."));

        if (passwordEncoder == null) {
            throw new RuntimeException("Encoder is NULL! Injection failed.");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid username or password.");
        }

        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public AdminUserResponse getByAdminId(String adminID) {

        User user = userRepository.findByAdminID(adminID)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return mapToAdminResponse(user);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (!passwordEncoder.matches(
                request.getOldPassword(),
                user.getPassword())) {

            throw new RuntimeException("Old password is incorrect.");
        }

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public void disableUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setEnabled(false);

        userRepository.save(user);
    }

    private AdminUserResponse mapToAdminResponse(User user) {

        AdminUserResponse response = new AdminUserResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setAdminID(user.getAdminID());
        response.setRole(user.getRole());
        response.setEnabled(user.isEnabled());

        return response;
    }
}