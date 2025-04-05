package com.luckyloot.game.slotmachine.spin.mapper;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.spin.dto.response.SpinResponse;
import com.luckyloot.game.slotmachine.spin.model.Spin;
import com.luckyloot.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class SpinMapper {
    public SpinResponse toSpinDto(Spin spin,String[][] result) {
        return new SpinResponse(
                spin.getId(),
                spin.getUser().getId(),
                spin.getSlotGame().getId(),
                result,
                spin.getWinAmount(),
                spin.getBetAmount(),
                spin.getStatus(),
                spin.getSpinTime()
        );
    }
}
