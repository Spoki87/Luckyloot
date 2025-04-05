package com.luckyloot.game.slotmachine.slot.service;

import com.luckyloot.exception.domain.ResourceNotFoundException;
import com.luckyloot.game.slotmachine.slot.dto.request.CreateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.request.UpdateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.response.SlotGameResponse;

import com.luckyloot.game.slotmachine.slot.mapper.SlotGameMapper;
import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.slot.repository.SlotGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SlotGameService {

    private final SlotGameRepository slotGameRepository;
    private final SlotGameMapper slotGameMapper;

    public SlotGameResponse create(CreateSlotGameRequest request) {
        SlotGame slotGame = slotGameMapper.fromCreateDto(request);
        slotGameRepository.save(slotGame);
        return slotGameMapper.fromSlotGame(slotGame);
    }

    public SlotGameResponse getSlot(UUID id) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));
        return slotGameMapper.fromSlotGame(slotGame);
    }

    public List<SlotGameResponse> getAll() {
        List<SlotGame> slotGames = slotGameRepository.findAll();
        return slotGames.stream().map(slotGameMapper::fromSlotGame).toList();
    }

    public SlotGameResponse update(UUID id, UpdateSlotGameRequest request) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));

        slotGameMapper.updateEntityFromDto(request, slotGame);

        SlotGame updatedSlotGame = slotGameRepository.save(slotGame);

        return slotGameMapper.fromSlotGame(updatedSlotGame);
    }

    public SlotGameResponse delete(UUID id) {
        SlotGame slotGame = slotGameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,id));
        slotGameRepository.delete(slotGame);
        return slotGameMapper.fromSlotGame(slotGame);
    }

}
