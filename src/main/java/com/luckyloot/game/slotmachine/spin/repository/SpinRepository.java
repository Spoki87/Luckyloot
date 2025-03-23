package com.luckyloot.game.slotmachine.spin.repository;

import com.luckyloot.game.slotmachine.spin.model.Spin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpinRepository extends JpaRepository<Spin, UUID> {
}