package com.luckyloot.game.slotmachine.spin.service;

import com.luckyloot.exception.domain.InvalidBetAmountException;
import com.luckyloot.exception.domain.ResourceNotFoundException;
import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.slot.repository.SlotGameRepository;
import com.luckyloot.game.slotmachine.spin.dto.request.CreateSpinRequest;
import com.luckyloot.game.slotmachine.spin.dto.response.SpinResponse;
import com.luckyloot.game.slotmachine.spin.mapper.SpinMapper;
import com.luckyloot.game.slotmachine.spin.model.Spin;
import com.luckyloot.game.slotmachine.spin.model.SpinStatus;
import com.luckyloot.game.slotmachine.spin.repository.SpinRepository;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpinService {

    private final SpinRepository spinRepository;
    private final SlotGameRepository slotGameRepository;
    private final SpinMapper spinMapper;
    private final WalletService walletService;

    public SpinResponse spin(UUID slotGameId, @Valid CreateSpinRequest request, User user) {

        SlotGame slotGame = slotGameRepository.findById(slotGameId)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,slotGameId));

        if (!slotGame.getBetAmounts().contains(request.getBetAmount())) {
            throw new InvalidBetAmountException(request);
        }

        walletService.bet(user, BigDecimal.valueOf(request.getBetAmount()));

        String[][] result;
        result = generateSpinSymbols(slotGame);

        boolean isWin = isWinningSpin(result);
        SpinStatus spinStatus = isWin ? SpinStatus.WIN : SpinStatus.LOSE;

        Spin spin = new Spin(
                user,
                slotGame,
                calculateWinAmount(result, request.getBetAmount()),
                request.getBetAmount(),
                spinStatus,
                LocalDateTime.now()
        );

        if(isWin){
            walletService.addWin(user, BigDecimal.valueOf(spin.getWinAmount()));
        }

        spinRepository.save(spin);

        return spinMapper.toSpinDto(spin,result);
    }

    private String[][] generateSpinSymbols(SlotGame slotGame) {
        String[][] result = new String[slotGame.getReels()][slotGame.getRows()];
        Random random = new Random();

        for (int i = 0; i < slotGame.getReels(); i++) {
            for (int j = 0; j < slotGame.getRows(); j++) {
                String randomSymbol = slotGame.getSymbols().get(random.nextInt(slotGame.getSymbols().size()));
                result[i][j] = randomSymbol;
            }
        }

        return result;
    }

    private boolean isWinningSpin(String[][] spinResult) {
        for (String[] strings : spinResult) {
            String firstSymbolInRow = strings[0];
            boolean isRowWinning = true;
            for (int col = 1; col < strings.length; col++) {
                if (!strings[col].equals(firstSymbolInRow)) {
                    isRowWinning = false;
                    break;
                }
            }
            if (isRowWinning) {
                return true;
            }
        }

        return false;
    }

    private double calculateWinAmount(String[][] spinResult, double betAmount) {
        if (isWinningSpin(spinResult)) {
            return betAmount * spinResult.length;
        } else {
            return 0;
        }
    }

}
