package com.luckyloot.user.appuser.dto.response;

import com.luckyloot.user.appuser.model.Role;
import com.luckyloot.user.appuser.model.User;
import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link User}
 */
@Value
public class UserResponse {
    UUID id;
    String username;
    String email;
    Role role;
}
