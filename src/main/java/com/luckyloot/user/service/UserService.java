package com.luckyloot.user.service;

import com.luckyloot.exception.ResourceAlreadyTakenException;
import com.luckyloot.exception.ResourceNotFoundException;
import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.dto.request.CreateUserRequest;
import com.luckyloot.user.dto.response.ConfirmationUserResponse;
import com.luckyloot.user.dto.response.UserResponse;
import com.luckyloot.user.model.Role;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "USER WITH EMAIL OR USERNAME %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,email)));
    }

    public ApiResponse<UserResponse> register(CreateUserRequest request){

        User user = new User(request.getUsername(),request.getEmail(),bCryptPasswordEncoder.encode(request.getPassword()), Role.USER);

        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            throw new ResourceAlreadyTakenException("Email already taken");
        }

        userRepository.save(user);
        confirmationTokenService.createToken(user);

        UserResponse userResponse = new UserResponse(user.getId(),user.getUsername(),user.getEmail(),user.getRole());

        return new ApiResponse<UserResponse>("User registered successfully",userResponse);
    }

    public ApiResponse<ConfirmationUserResponse> confirmRegistration(String token){

        confirmationTokenService.confirmToken(token);

        ConfirmationUserResponse confirmationUserResponse = new ConfirmationUserResponse(LocalDateTime.now());

        return new ApiResponse<ConfirmationUserResponse>("User confirmed",confirmationUserResponse);
    }
}
