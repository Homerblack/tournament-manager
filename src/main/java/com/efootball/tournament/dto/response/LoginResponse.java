package com.efootball.tournament.dto.response;

import com.efootball.tournament.enums.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private String username;

    private String fullName;

    private Role role;
}
