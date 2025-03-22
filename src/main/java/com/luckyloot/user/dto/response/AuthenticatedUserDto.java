package com.luckyloot.user.dto.response;

import com.luckyloot.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class AuthenticatedUserDto {
    Role role;
    String token;
}
