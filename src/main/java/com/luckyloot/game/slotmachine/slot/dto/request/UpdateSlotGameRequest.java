package com.luckyloot.game.slotmachine.slot.dto.request;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for updating {@link SlotGame}
 */
@Value
public class UpdateSlotGameRequest implements Serializable {

    @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    @NotBlank(message = "name is required")
    String name;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    @NotNull(message = "Reels are required")
    @Range(message = "Reels range is between 3 and 6", min = 3, max = 5)
    int reels;

    @NotNull(message = "Rows are required")
    @Range(message = "Rows range is between 3 and 5", min = 3, max = 5)
    int rows;

    @NotEmpty(message = "symbols is required")
    @Size(min = 2, max = 10)
    List<String> symbols;

    @NotEmpty(message = "Bet amounts are required")
    @Size(min = 1, message = "There should be at least one bet amount")
    List<Double> betAmounts;

    @URL(message = "Invalid image URL")
    String imagePath;

}
