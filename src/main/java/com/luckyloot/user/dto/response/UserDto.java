package com.luckyloot.user.dto.response;

import com.luckyloot.user.model.Role;
import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class UserDto {
    UUID id;
    String username;
    String email;
    Role role;
}
