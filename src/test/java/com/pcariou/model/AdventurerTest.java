package com.pcariou.model;

import com.pcariou.model.tile.Mountain;
import com.pcariou.service.MovementService;
import com.pcariou.util.Direction;
import com.pcariou.util.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Test
    void testTurnLeft() {
        Direction dir = Direction.NORTH;
        assertEquals(Direction.WEST, dir.turnLeft());
    }

    @Test
    void testTurnRight() {
        Direction dir = Direction.NORTH;
        assertEquals(Direction.EAST, dir.turnRight());
    }

    @Test
    void testMoveForward() {
        Map map = new Map(3, 3);
        Adventurer adventurer = new Adventurer("Lara", Position.of(1, 1), Direction.NORTH, "A");

        MovementService.moveOneStep(adventurer, map, List.of());
        assertEquals(Position.of(1, 0), adventurer.getPosition());
    }

    @Test
    void testAvoidMountain() {
        Map map = new Map(3, 3);
        map.setTile(Position.of(1, 0), new Mountain());

        Adventurer adventurer = new Adventurer("Lara", Position.of(1, 1), Direction.NORTH, "A");
        MovementService.moveOneStep(adventurer, map, List.of());

        assertEquals(Position.of(1, 1), adventurer.getPosition()); // Should not move
    }
}