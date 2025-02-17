package com.pcariou.service;

import com.pcariou.model.Adventurer;
import com.pcariou.model.Map;
import com.pcariou.model.tile.Treasure;
import com.pcariou.util.Position;

import java.util.List;

public class MovementService {

    private MovementService() {}

    public static void moveOneStep(Adventurer adventurer, Map map, List<Adventurer> otherAdventurers) {
        if (!adventurer.hasMoreMoves()) {
            return;
        }

        char move = adventurer.getMovements().poll();
        switch (move) {
            case 'A' -> {
                Position next = adventurer.getDirection().moveForward(adventurer.getPosition());
                if (map.isWalkable(next, otherAdventurers)) {
                    adventurer.setPosition(next);
                    collectTreasure(adventurer, map);
                }
            }
            case 'G' -> adventurer.setDirection(adventurer.getDirection().turnLeft());
            case 'D' -> adventurer.setDirection(adventurer.getDirection().turnRight());
        }
    }

    private static void collectTreasure(Adventurer adventurer, Map map) {
        if (map.getTile(adventurer.getPosition()) instanceof Treasure treasure) {
            if (!treasure.isEmpty()) {
                treasure.decreaseCount();
                adventurer.addTreasure();
            }
        }
    }
}
