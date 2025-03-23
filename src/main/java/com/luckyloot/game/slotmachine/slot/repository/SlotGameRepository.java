package com.luckyloot.game.slotmachine.slot.repository;

import com.luckyloot.game.slotmachine.slot.model.SlotGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlotGameRepository extends JpaRepository<SlotGame, UUID>{
}