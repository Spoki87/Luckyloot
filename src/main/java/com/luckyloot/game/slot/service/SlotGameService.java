package com.luckyloot.game.slot.service;

import com.luckyloot.exception.ResourceNotFoundException;
import com.luckyloot.game.slot.dto.request.CreateSlotGameDto;
import com.luckyloot.game.slot.dto.request.UpdateSlotGameDto;
import com.luckyloot.game.slot.dto.response.SlotGameDto;

import com.luckyloot.game.slot.mapper.SlotGameMapper;
import com.luckyloot.game.slot.model.SlotGame;
import com.luckyloot.game.slot.repository.SlotGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SlotGameService {

    private final SlotGameRepository slotGameRepository;
    private final SlotGameMapper slotGameMapper;

    public SlotGameDto create(CreateSlotGameDto request) {
        SlotGame slotGame = slotGameMapper.fromCreateDto(request);
        slotGameRepository.save(slotGame);
        return slotGameMapper.fromSlotGame(slotGame);
    }

    public SlotGameDto getSlot(UUID id) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));
        return slotGameMapper.fromSlotGame(slotGame);
    }

    public List<SlotGameDto> getAll() {
        List<SlotGame> slotGames = slotGameRepository.findAll();
        return slotGames.stream().map(slotGameMapper::fromSlotGame).toList();
    }

    public SlotGameDto update(UUID id, UpdateSlotGameDto request) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));

        slotGameMapper.updateEntityFromDto(request, slotGame);

        SlotGame updatedSlotGame = slotGameRepository.save(slotGame);

        return slotGameMapper.fromSlotGame(updatedSlotGame);
    }

    public SlotGameDto delete(UUID id) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));
        slotGameRepository.delete(slotGame);
        return slotGameMapper.fromSlotGame(slotGame);
    }

}
