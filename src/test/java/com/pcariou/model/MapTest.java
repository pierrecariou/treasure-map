package com.pcariou.model;

import com.pcariou.model.tile.Mountain;
import com.pcariou.model.tile.PlainTile;
import com.pcariou.model.tile.Treasure;
import com.pcariou.util.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void testMapInitialization() {
        Map map = new Map(3, 3);
        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeight());
    }

    @Test
    void testTilePlacement() {
        Map map = new Map(3, 3);
        map.setTile(Position.of(1, 1), new Mountain());
        assertFalse(map.getTile(Position.of(1, 1)).isWalkable());

        map.setTile(Position.of(2, 2), new Treasure(3));
        assertTrue(map.getTile(Position.of(2, 2)).isWalkable());
    }

    @Test
    void testWalkableTiles() {
        Map map = new Map(3, 3);
        map.setTile(Position.of(1, 1), new Mountain());
        map.setTile(Position.of(2, 2), new PlainTile());

        assertFalse(map.isWalkable(Position.of(1, 1)));  // Mountain is not walkable
        assertTrue(map.isWalkable(Position.of(2, 2)));  // Plain is walkable
    }
}