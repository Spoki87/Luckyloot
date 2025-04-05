package com.luckyloot.game.slotmachine.spin.dto.response;

import com.luckyloot.game.slotmachine.spin.model.SpinStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class SpinResponse {

    private UUID id;

    private UUID userId;

    private UUID slotGameId;

    private String[][] result;

    private double winAmount;

    private double betAmount;

    private SpinStatus status;

    private LocalDateTime spinTime;
}
