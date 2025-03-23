package com.luckyloot.game.slotmachine.spin.dto;

import com.luckyloot.game.slotmachine.spin.model.SpinStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class SpinDto {

    private UUID id;

    private UUID userId;

    private UUID slotGameId;

    private List<String> result;

    private double winAmount;

    private double betAmount;

    private SpinStatus status;

    private LocalDateTime spinTime;
}
