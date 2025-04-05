package com.luckyloot.game.slotmachine.slot.mapper;

import com.luckyloot.game.slotmachine.slot.dto.request.CreateSlotGameDto;
import com.luckyloot.game.slotmachine.slot.dto.request.UpdateSlotGameDto;
import com.luckyloot.game.slotmachine.slot.dto.response.SlotGameDto;
import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import org.springframework.stereotype.Component;

@Component
public class SlotGameMapper {

    public SlotGame fromCreateDto(CreateSlotGameDto dto){
        return new SlotGame(
                dto.getName(),
                dto.getDescription(),
                dto.getReels(),
                dto.getSymbols(),
                dto.getBetAmounts(),
                dto.getImagePath()
        );
    }

    public SlotGameDto fromSlotGame(SlotGame slotGame){
        return new SlotGameDto(
                slotGame.getId(),
                slotGame.getName(),
                slotGame.getDescription(),
                slotGame.getReels(),
                slotGame.getSymbols(),
                slotGame.getBetAmounts(),
                slotGame.getImagePath()
        );
    }

    public void updateEntityFromDto(UpdateSlotGameDto request, SlotGame slotGame) {
        slotGame.setName(request.getName());
        slotGame.setDescription(request.getDescription());
        slotGame.setReels(request.getReels());
        slotGame.setSymbols(request.getSymbols());
        slotGame.setBetAmounts(request.getBetAmounts());
        slotGame.setImagePath(request.getImagePath());
    }
}
