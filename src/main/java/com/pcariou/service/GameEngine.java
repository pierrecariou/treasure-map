package com.pcariou.service;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.model.tile.Tile;
import com.pcariou.util.Position;

import java.util.List;

public class GameEngine {
    private final Map map;
    private final List<Adventurer> adventurers;
    private boolean verbose = false;

    public GameEngine(Map map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }

    public void run() {
        int turn = 0;
        printState(turn++);
        while (Adventurer.hasMoreMoves(adventurers)) {
            for (Adventurer adventurer : adventurers) {
                List<Adventurer> otherAdventurers = adventurers.stream().filter(a -> a != adventurer).toList();
                MovementService.moveOneStep(adventurer, map, otherAdventurers);
            }
            printState(turn++);
        }
    }

    public GameEngine verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    // Helper method to print the state of the map at a given turn
    private void printState(int turn) {
        if (!verbose) {
            return;
        }
        System.out.println("Map State (Turn " + turn + "):");

        for (int y = 0; y < map.getHeight(); y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < map.getWidth(); x++) {
                Tile tile = map.getTile(Position.of(x, y));
                String cell = tile.toString();

                for (Adventurer adventurer : adventurers) {
                    if (adventurer.getPosition().equals(Position.of(x, y))) {
                        cell = adventurer.toCell();
                        break;
                    }
                }
                row.append(String.format("%-6s", cell));
            }
            System.out.println(row);
        }
        System.out.println();
    }
}