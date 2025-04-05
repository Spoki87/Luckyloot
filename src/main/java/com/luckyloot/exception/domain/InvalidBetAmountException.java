package com.luckyloot.exception.domain;

import com.luckyloot.game.slotmachine.spin.dto.CreateSpinDto;

public class InvalidBetAmountException extends RuntimeException {
    public InvalidBetAmountException(CreateSpinDto spinDto) {
        super("Invalid slot bet amount " + spinDto.getBetAmount());
    }
}
