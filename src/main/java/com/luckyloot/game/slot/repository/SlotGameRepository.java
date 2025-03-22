package com.luckyloot.game.slot.repository;

import com.luckyloot.game.slot.model.SlotGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlotGameRepository extends JpaRepository<SlotGame, UUID>{
}