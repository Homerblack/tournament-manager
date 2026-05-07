package com.efootball.tournament.dto.response;

import com.efootball.tournament.enums.Role;
import lombok.Data;

@Data
public class AdminUserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String adminID;
    private Role role;
    private boolean enabled;
}
