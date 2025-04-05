package com.luckyloot.game.slotmachine.slot.controller;

import com.luckyloot.game.slotmachine.slot.dto.request.CreateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.request.UpdateSlotGameRequest;
import com.luckyloot.game.slotmachine.slot.dto.response.SlotGameResponse;
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
    public ResponseEntity<ApiResponse<SlotGameResponse>> createSlot(@Valid @RequestBody CreateSlotGameRequest request){
        SlotGameResponse slotGameResponse = slotGameService.create(request);
        return ResponseEntity.ok(ApiResponse.success(slotGameResponse));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<SlotGameResponse>>> getAllSlots(){
        List<SlotGameResponse> slotGameResponse = slotGameService.getAll();
        return ResponseEntity.ok(ApiResponse.success(slotGameResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameResponse>> getSlot(@PathVariable UUID id){
        SlotGameResponse slotGameResponse = slotGameService.getSlot(id);
        return ResponseEntity.ok(ApiResponse.success(slotGameResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameResponse>> updateSlot(@Valid @RequestBody UpdateSlotGameRequest request, @PathVariable UUID id){
        SlotGameResponse slotGameResponse = slotGameService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(slotGameResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotGameResponse>> deleteSlot(@PathVariable UUID id){
        SlotGameResponse slotGameResponse = slotGameService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(slotGameResponse));
    }

}
