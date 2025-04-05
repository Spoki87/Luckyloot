package com.luckyloot.user.registrationtoken.repository;

import com.luckyloot.user.registrationtoken.model.RegistrationUserToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistrationUserTokenRepository extends JpaRepository<RegistrationUserToken, UUID> {

    Optional<RegistrationUserToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE RegistrationUserToken c SET c.confirmedTime = :confirmationTime WHERE c.token = :token")
    int updateConfirmedTime(String token, LocalDateTime confirmationTime);
}
