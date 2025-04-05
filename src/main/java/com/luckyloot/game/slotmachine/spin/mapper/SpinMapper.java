package com.luckyloot.game.slotmachine.spin.mapper;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.spin.dto.response.SpinResponse;
import com.luckyloot.game.slotmachine.spin.model.Spin;
import com.luckyloot.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class SpinMapper {
    public SpinResponse toSpinDto(Spin spin) {
        return new SpinResponse(
                spin.getId(),
                spin.getUser().getId(),
                spin.getSlotGame().getId(),
                spin.getResult(),
                spin.getWinAmount(),
                spin.getBetAmount(),
                spin.getStatus(),
                spin.getSpinTime()
        );
    }

    public Spin toSpin(SpinResponse spinResponse, User user, SlotGame slotGame) {
        return new Spin(
                user,
                slotGame,
                spinResponse.getResult(),
                spinResponse.getWinAmount(),
                spinResponse.getBetAmount(),
                spinResponse.getStatus(),
                spinResponse.getSpinTime()
        );
    }
}
