package com.luckyloot.game.slotmachine.spin.model;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Spin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_game_id", nullable = false)
    private SlotGame slotGame;

    private double winAmount;

    private double betAmount;

    @Enumerated(EnumType.STRING)
    private SpinStatus status;

    private LocalDateTime spinTime;

    public Spin(User user, SlotGame slotGame, double winAmount, double betAmount, SpinStatus status, LocalDateTime spinTime) {
        this.user = user;
        this.slotGame = slotGame;
        this.winAmount = winAmount;
        this.betAmount = betAmount;
        this.status = status;
        this.spinTime = spinTime;
    }
}
