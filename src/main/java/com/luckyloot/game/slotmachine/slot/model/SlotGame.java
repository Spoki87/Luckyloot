package com.luckyloot.game.slotmachine.slot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SlotGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;

    private int reels;

    @ElementCollection
    private List<String> symbols;

    @ElementCollection
    private List<Double> betAmounts;

    private String imagePath;

    public SlotGame(String name, String description, int reels, List<String> symbols, List<Double> betAmounts, String imagePath) {
        this.name = name;
        this.description = description;
        this.reels = reels;
        this.symbols = symbols;
        this.betAmounts = betAmounts;
        this.imagePath = imagePath;
    }
}
