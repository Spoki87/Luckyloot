package com.luckyloot.game.slotmachine.spin.controller;

import com.luckyloot.game.slotmachine.spin.dto.CreateSpinDto;
import com.luckyloot.game.slotmachine.spin.dto.SpinDto;
import com.luckyloot.game.slotmachine.spin.service.SpinService;
import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/spins")
@Valid
@AllArgsConstructor
public class SpinController {
    private final SpinService spinService;

    @PostMapping("/{slotGameId}")
    public ResponseEntity<ApiResponse<SpinDto>> spin(@Valid @RequestBody CreateSpinDto request, @PathVariable("slotGameId") UUID slotGameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        SpinDto spinDto = spinService.spin(slotGameId,request,user);

        return ResponseEntity.ok(ApiResponse.success(spinDto));
    }

}
