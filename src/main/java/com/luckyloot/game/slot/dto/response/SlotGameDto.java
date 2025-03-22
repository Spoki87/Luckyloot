package com.luckyloot.game.slot.dto.response;

import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.luckyloot.game.slot.model.SlotGame}
 */
@Value
public class SlotGameDto implements Serializable {
    UUID id;
    String name;
    String description;
    int reels;
    int rows;
    List<String> symbols;
    List<Double> betAmounts;
    double winChance;
    String imagePath;
}