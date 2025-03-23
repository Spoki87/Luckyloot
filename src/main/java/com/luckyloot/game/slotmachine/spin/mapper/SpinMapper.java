package com.luckyloot.game.slotmachine.spin.mapper;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.spin.dto.SpinDto;
import com.luckyloot.game.slotmachine.spin.model.Spin;
import com.luckyloot.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class SpinMapper {
    public SpinDto toSpinDto(Spin spin) {
        return new SpinDto(
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

    public Spin toSpin(SpinDto spinDto, User user, SlotGame slotGame) {
        return new Spin(
                user,
                slotGame,
                spinDto.getResult(),
                spinDto.getWinAmount(),
                spinDto.getBetAmount(),
                spinDto.getStatus(),
                spinDto.getSpinTime()
        );
    }
}
