package com.luckyloot.user.controller;

import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.dto.request.AuthenticateUserDto;
import com.luckyloot.user.dto.request.CreateUserDto;
import com.luckyloot.user.dto.response.AuthenticatedUserDto;
import com.luckyloot.user.dto.response.ConfirmedUserDto;
import com.luckyloot.user.dto.response.UserDto;
import com.luckyloot.user.service.AuthenticationService;
import com.luckyloot.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody CreateUserDto request) {
        UserDto userDto = userService.register(request);
        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @GetMapping("/confirm")
    public ResponseEntity<ApiResponse<ConfirmedUserDto>> confirmRegistration(@RequestParam String token) {
        ConfirmedUserDto confirmedUserDto = userService.confirmRegistration(token);
        return ResponseEntity.ok(ApiResponse.success(confirmedUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticatedUserDto>> login(@Valid @RequestBody AuthenticateUserDto request) {
        AuthenticatedUserDto authenticatedUserDto = authenticationService.Authenticate(request);
        return ResponseEntity.ok(ApiResponse.success(authenticatedUserDto));
    }
}
