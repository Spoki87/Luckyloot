package com.luckyloot.game.slotmachine.slot.mapper;

import com.luckyloot.game.slotmachine.slot.dto.request.CreateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.request.UpdateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.response.SlotGameResponse;
import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import org.springframework.stereotype.Component;

@Component
public class SlotGameMapper {

    public SlotGame fromCreateDto(CreateSlotGameRequest dto){
        return new SlotGame(
                dto.getName(),
                dto.getDescription(),
                dto.getRows(),
                dto.getReels(),
                dto.getSymbols(),
                dto.getBetAmounts(),
                dto.getImagePath()
        );
    }

    public SlotGameResponse fromSlotGame(SlotGame slotGame){
        return new SlotGameResponse(
                slotGame.getId(),
                slotGame.getName(),
                slotGame.getDescription(),
                slotGame.getRows(),
                slotGame.getReels(),
                slotGame.getSymbols(),
                slotGame.getBetAmounts(),
                slotGame.getImagePath()
        );
    }

    public void updateEntityFromDto(UpdateSlotGameRequest request, SlotGame slotGame) {
        slotGame.setName(request.getName());
        slotGame.setDescription(request.getDescription());
        slotGame.setRows(request.getRows());
        slotGame.setReels(request.getReels());
        slotGame.setSymbols(request.getSymbols());
        slotGame.setBetAmounts(request.getBetAmounts());
        slotGame.setImagePath(request.getImagePath());
    }
}
