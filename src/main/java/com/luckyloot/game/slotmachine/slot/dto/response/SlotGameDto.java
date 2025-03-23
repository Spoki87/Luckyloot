package com.luckyloot.game.slotmachine.slot.dto.response;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link SlotGame}
 */
@Value
public class SlotGameDto implements Serializable {
    UUID id;
    String name;
    String description;
    int reels;
    List<String> symbols;
    List<Double> betAmounts;
    double winChance;
    String imagePath;
}