package com.luckyloot.game.slotmachine.spin.service;

import com.luckyloot.exception.domain.InvalidBetAmountException;
import com.luckyloot.exception.domain.ResourceNotFoundException;
import com.luckyloot.game.slotmachine.slot.mapper.SlotGameMapper;
import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import com.luckyloot.game.slotmachine.slot.repository.SlotGameRepository;
import com.luckyloot.game.slotmachine.spin.dto.CreateSpinDto;
import com.luckyloot.game.slotmachine.spin.dto.SpinDto;
import com.luckyloot.game.slotmachine.spin.mapper.SpinMapper;
import com.luckyloot.game.slotmachine.spin.model.Spin;
import com.luckyloot.game.slotmachine.spin.model.SpinStatus;
import com.luckyloot.game.slotmachine.spin.repository.SpinRepository;
import com.luckyloot.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpinService {

    private final SpinRepository spinRepository;
    private final SlotGameRepository slotGameRepository;
    private final SpinMapper spinMapper;
    private final SlotGameMapper slotGameMapper;

    public SpinDto spin(UUID slotGameId, @Valid CreateSpinDto request, User user) {

        SlotGame slotGame = slotGameRepository.findById(slotGameId)
                .orElseThrow(()->new ResourceNotFoundException(SlotGame.class,slotGameId));

        if (!slotGame.getBetAmounts().contains(request.getBetAmount())) {
            throw new InvalidBetAmountException(request);
        }

        List<String> result = generateSpinSymbols(slotGame);

        boolean isWin = isWinningSpin(result);
        SpinStatus spinStatus = isWin ? SpinStatus.WIN : SpinStatus.LOSE;

        Spin spin = new Spin(
                user,
                slotGame,
                result,
                calculateWinAmount(result, request.getBetAmount()),
                request.getBetAmount(),
                spinStatus,
                LocalDateTime.now()
        );

        spinRepository.save(spin);

        return spinMapper.toSpinDto(spin);
    }

    private List<String> generateSpinSymbols(SlotGame slotGame) {
        List<String> result = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < slotGame.getReels(); i++) {
            String randomSymbol = slotGame.getSymbols().get(random.nextInt(slotGame.getSymbols().size()));
            result.add(randomSymbol);
        }

        return result;
    }

    private boolean isWinningSpin(List<String> spinResult) {
        String firstSymbol = spinResult.get(0);
        for (String symbol : spinResult) {
            if (!symbol.equals(firstSymbol)) {
                return false;
            }
        }
        return true;
    }

    private double calculateWinAmount(List<String> spinResult, double betAmount) {
        if(isWinningSpin(spinResult)) {
            return betAmount * spinResult.size();
        }else{
            return 0;
        }
    }

}
