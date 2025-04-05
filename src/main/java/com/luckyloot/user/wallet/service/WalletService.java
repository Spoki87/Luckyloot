package com.luckyloot.user.wallet.service;

import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.wallet.dto.DepositRequest;
import com.luckyloot.user.wallet.dto.WithdrawalRequest;
import com.luckyloot.user.wallet.model.Wallet;
import com.luckyloot.user.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public void deposit(User user, DepositRequest request) {
        Wallet wallet = user.getWallet();
        wallet.add(request.getAmount());
        walletRepository.save(wallet);
    }

    public void withdrawal(User user, WithdrawalRequest request) {
        Wallet wallet = user.getWallet();
        wallet.subtract(request.getAmount());
        walletRepository.save(wallet);
    }

    public void bet(User user, BigDecimal amount){
        Wallet wallet = user.getWallet();
        wallet.subtract(amount);
        walletRepository.save(wallet);
    }

    public void addWin(User user, BigDecimal amount) {
        Wallet wallet = user.getWallet();
        wallet.add(amount);
        walletRepository.save(wallet);
    }
}
