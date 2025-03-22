package com.luckyloot.user.repository;

import com.luckyloot.user.model.ConfirmationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, UUID> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedTime = :confirmationTime WHERE c.token = :token")
    int updateConfirmedTime(String token, LocalDateTime confirmationTime);
}
