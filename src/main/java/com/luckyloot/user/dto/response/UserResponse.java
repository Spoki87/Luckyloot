package com.luckyloot.user.dto.response;

import com.luckyloot.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private Role role;
}
