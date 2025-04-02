package com.luckyloot.user.controller;

import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.dto.request.*;
import com.luckyloot.user.dto.response.AuthenticatedUserDto;
import com.luckyloot.user.dto.response.ConfirmedUserDto;
import com.luckyloot.user.dto.response.UserDto;
import com.luckyloot.user.model.User;
import com.luckyloot.user.service.AuthenticationService;
import com.luckyloot.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody ChangePasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        userService.changePassword(user,request);
        return ResponseEntity.ok(ApiResponse.success("Password changed"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@Valid @RequestBody ResetPasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        userService.resetPassword(user,request);
        return ResponseEntity.ok(ApiResponse.success("Email with link to reset your password has been sent"));
    }

    @PostMapping("/new-password")
    public ResponseEntity<ApiResponse<String>> newPassword(@Valid @RequestBody NewPasswordRequest request){
        userService.setNewPassword(request);
        return ResponseEntity.ok(ApiResponse.success("New password has been sent"));
    }


}
