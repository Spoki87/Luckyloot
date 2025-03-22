package com.luckyloot.game.slot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class SlotGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;

    private int reels;
    private int rows;

    @ElementCollection
    private List<String> symbols;

    @ElementCollection
    private List<Double> betAmounts;

    private double winChance;

    private String imagePath;

    public SlotGame(String name, String description, int reels, int rows, List<String> symbols, List<Double> betAmounts, double winChance, String imagePath) {
        this.name = name;
        this.description = description;
        this.reels = reels;
        this.rows = rows;
        this.symbols = symbols;
        this.betAmounts = betAmounts;
        this.winChance = winChance;
        this.imagePath = imagePath;
    }
}
