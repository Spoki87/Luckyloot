package com.luckyloot.exception.domain;

import com.luckyloot.game.slotmachine.spin.dto.request.CreateSpinRequest;

public class InvalidBetAmountException extends RuntimeException {
    public InvalidBetAmountException(CreateSpinRequest spinDto) {
        super("Invalid slot bet amount " + spinDto.getBetAmount());
    }
}
