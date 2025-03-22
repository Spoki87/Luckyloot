package com.luckyloot.user.controller;

import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.dto.request.AuthenticationRequest;
import com.luckyloot.user.dto.request.CreateUserRequest;
import com.luckyloot.user.dto.response.AuthenticationResponse;
import com.luckyloot.user.dto.response.ConfirmationUserResponse;
import com.luckyloot.user.dto.response.UserResponse;
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
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/confirm")
    public ResponseEntity<ApiResponse<ConfirmationUserResponse>> confirmRegistration(@RequestParam String token){
        return ResponseEntity.ok(userService.confirmRegistration(token));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.Authenticate(request));
    }
}
