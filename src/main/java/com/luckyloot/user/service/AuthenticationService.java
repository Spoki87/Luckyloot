package com.luckyloot.user.service;

import com.luckyloot.exception.ResourceNotFoundException;
import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.dto.request.AuthenticationRequest;
import com.luckyloot.user.dto.response.AuthenticationResponse;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.UserRepository;
import com.luckyloot.user.security.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ApiResponse<AuthenticationResponse> Authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(user.getRole(),jwtToken);

        return new ApiResponse<AuthenticationResponse>("User authenticated",authenticationResponse);
    }
}
