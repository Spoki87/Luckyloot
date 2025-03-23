package com.luckyloot.game.slotmachine.slot.controller;

import com.luckyloot.game.slotmachine.slot.dto.request.CreateSlotGameDto;
import com.luckyloot.game.slotmachine.slot.dto.request.UpdateSlotGameDto;
import com.luckyloot.game.slotmachine.slot.dto.response.SlotGameDto;
import com.luckyloot.game.slotmachine.slot.service.SlotGameService;
import com.luckyloot.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/slots")
public class SlotGameController {
    private final SlotGameService slotGameService;

    @PostMapping()
    public ResponseEntity<ApiResponse<SlotGameDto>> createSlot(@Valid @RequestBody CreateSlotGameDto request){
        SlotGameDto slotGameDto = slotGameService.create(request);
        return ResponseEntity.ok(ApiResponse.success(slotGameDto));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<SlotGameDto>>> getAllSlots(){
        List<SlotGameDto> slotGameDto = slotGameService.getAll();
        return ResponseEntity.ok(ApiResponse.success(slotGameDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameDto>> getSlot(@PathVariable UUID id){
        SlotGameDto slotGameDto = slotGameService.getSlot(id);
        return ResponseEntity.ok(ApiResponse.success(slotGameDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameDto>> updateSlot(@Valid @RequestBody UpdateSlotGameDto request, @PathVariable UUID id){
        SlotGameDto slotGameDto = slotGameService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(slotGameDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameDto>> deleteSlot(@PathVariable UUID id){
        SlotGameDto slotGameDto = slotGameService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(slotGameDto));
    }

}
