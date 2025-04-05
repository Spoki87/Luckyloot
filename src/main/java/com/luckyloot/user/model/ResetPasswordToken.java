package com.luckyloot.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String token;

    private LocalDateTime createdTime;

    private LocalDateTime expiredTime;

    private LocalDateTime usedTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private User user;

    public ResetPasswordToken(User user, String token){
        this.createdTime = LocalDateTime.now();
        this.expiredTime = LocalDateTime.now().plusHours(12);
        this.token = token;
        this.user = user;
    }

    public void setUsedTime(LocalDateTime usedTime){
        this.usedTime = LocalDateTime.now();
    }
}
