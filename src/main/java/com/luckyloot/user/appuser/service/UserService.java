package com.luckyloot.user.appuser.service;

import com.luckyloot.exception.domain.NewPasswordException;
import com.luckyloot.exception.domain.ResourceAlreadyTakenException;
import com.luckyloot.exception.domain.UserNotFoundException;
import com.luckyloot.user.appuser.dto.request.ChangePasswordRequest;
import com.luckyloot.user.appuser.dto.request.CreateUserRequest;
import com.luckyloot.user.appuser.dto.request.NewPasswordRequest;
import com.luckyloot.user.appuser.dto.request.ResetPasswordRequest;
import com.luckyloot.user.appuser.dto.response.UserResponse;
import com.luckyloot.user.appuser.model.Role;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.wallet.model.Wallet;
import com.luckyloot.user.appuser.repository.UserRepository;
import com.luckyloot.user.wallet.repository.WalletRepository;
import com.luckyloot.user.registrationtoken.service.RegistrationUserTokenService;
import com.luckyloot.user.resetpasswordtoken.service.ResetPasswordTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationUserTokenService registrationUserTokenService;
    private final ResetPasswordTokenService resetPasswordTokenService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponse register(CreateUserRequest request){

        User user = new User(request.getUsername(),request.getEmail(),bCryptPasswordEncoder.encode(request.getPassword()), Role.USER);

        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            throw new ResourceAlreadyTakenException("Email already taken");
        }

        userRepository.save(user);

        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);

        registrationUserTokenService.createToken(user);

        return new UserResponse(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

    public void confirmRegistration(String token){

        registrationUserTokenService.confirmToken(token);
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

    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        resetPasswordTokenService.createToken(user);
    }

    public void setNewPassword(NewPasswordRequest request) {
       User user = resetPasswordTokenService.useToken(request.getResetToken());
       user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
       userRepository.save(user);
    }
}
