package com.luckyloot.user.service;

import com.luckyloot.exception.NewPasswordException;
import com.luckyloot.exception.ResourceAlreadyTakenException;
import com.luckyloot.exception.UserNotFoundException;
import com.luckyloot.user.dto.request.ChangePasswordRequest;
import com.luckyloot.user.dto.request.CreateUserDto;
import com.luckyloot.user.dto.request.NewPasswordRequest;
import com.luckyloot.user.dto.request.ResetPasswordRequest;
import com.luckyloot.user.dto.response.ConfirmedUserDto;
import com.luckyloot.user.dto.response.UserDto;
import com.luckyloot.user.model.Role;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserDto register(CreateUserDto request){

        User user = new User(request.getUsername(),request.getEmail(),bCryptPasswordEncoder.encode(request.getPassword()), Role.USER);

        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            throw new ResourceAlreadyTakenException("Email already taken");
        }

        userRepository.save(user);
        confirmationTokenService.createToken(user);

        return new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

    public ConfirmedUserDto confirmRegistration(String token){

        confirmationTokenService.confirmToken(token);

        return new ConfirmedUserDto(LocalDateTime.now());
    }


    public void changePassword(User user, ChangePasswordRequest request) {
        if(!bCryptPasswordEncoder.encode(user.getPassword()).equals(request.getCurrentPassword())){
            throw new NewPasswordException("Current password doesn't match");
        }

        if(bCryptPasswordEncoder.encode(user.getPassword()).equals(request.getNewPassword())){
            throw new NewPasswordException("Current password cannot be the same as the new password");
        }

        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void resetPassword(User user, ResetPasswordRequest request) {
    }

    public void setNewPassword(@Valid NewPasswordRequest request) {
    }
}
