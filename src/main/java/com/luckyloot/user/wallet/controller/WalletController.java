package com.luckyloot.user.wallet.controller;

import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.wallet.dto.DepositRequest;
import com.luckyloot.user.wallet.dto.WithdrawalRequest;
import com.luckyloot.user.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<String>> deposit(@AuthenticationPrincipal User user, @RequestBody @Valid DepositRequest request) {
        walletService.deposit(user,request);
        return ResponseEntity.ok(ApiResponse.success("Deposit successful"));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<ApiResponse<String>> withdrawal(@AuthenticationPrincipal User user, @RequestBody @Valid WithdrawalRequest request){
        walletService.withdrawal(user,request);
        return ResponseEntity.ok(ApiResponse.success("Withdrawal successful"));
    }
}
