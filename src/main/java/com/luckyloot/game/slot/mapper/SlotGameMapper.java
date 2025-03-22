package com.luckyloot.game.slot.mapper;

import com.luckyloot.game.slot.dto.request.CreateSlotGameDto;
import com.luckyloot.game.slot.dto.request.UpdateSlotGameDto;
import com.luckyloot.game.slot.dto.response.SlotGameDto;
import com.luckyloot.game.slot.model.SlotGame;
import org.springframework.stereotype.Component;

@Component
public class SlotGameMapper {

    public SlotGame fromCreateDto(CreateSlotGameDto dto){
        return new SlotGame(
                dto.getName(),
                dto.getDescription(),
                dto.getReels(),
                dto.getRows(),
                dto.getSymbols(),
                dto.getBetAmounts(),
                dto.getWinChance(),
                dto.getImagePath()
        );
    }

    public SlotGame fromUpdateDto(UpdateSlotGameDto dto){
        return new SlotGame(
                dto.getName(),
                dto.getDescription(),
                dto.getReels(),
                dto.getRows(),
                dto.getSymbols(),
                dto.getBetAmounts(),
                dto.getWinChance(),
                dto.getImagePath()
        );
    }

    public SlotGameDto fromSlotGame(SlotGame slotGame){
        return new SlotGameDto(
                slotGame.getId(),
                slotGame.getName(),
                slotGame.getDescription(),
                slotGame.getReels(),
                slotGame.getRows(),
                slotGame.getSymbols(),
                slotGame.getBetAmounts(),
                slotGame.getWinChance(),
                slotGame.getImagePath()
        );
    }

    public void updateEntityFromDto(UpdateSlotGameDto request, SlotGame slotGame) {
        slotGame.setName(request.getName());
        slotGame.setDescription(request.getDescription());
        slotGame.setReels(request.getReels());
        slotGame.setRows(request.getRows());
        slotGame.setSymbols(request.getSymbols());
        slotGame.setBetAmounts(request.getBetAmounts());
        slotGame.setWinChance(request.getWinChance());
        slotGame.setImagePath(request.getImagePath());
    }
}
