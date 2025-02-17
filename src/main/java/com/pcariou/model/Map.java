package com.pcariou.model;

import com.pcariou.model.tile.PlainTile;
import com.pcariou.model.tile.Tile;
import com.pcariou.util.Position;

import java.util.List;

public class Map {
    private final Tile[][] grid;
    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        initializeMap();
    }

    private void initializeMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new PlainTile();
            }
        }
    }

    public boolean isWalkable(Position position, List<Adventurer> adventurers) {
        return isValidPosition(position.x(), position.y()) &&
                grid[position.x()][position.y()].isWalkable() &&
                adventurers.stream().noneMatch(a -> a.getPosition().equals(position));
    }

    public boolean isWalkable(Position position) {
        return isValidPosition(position.x(), position.y()) &&
                grid[position.x()][position.y()].isWalkable();
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Tile getTile(Position position) {
        return getTile(position.x(), position.y());
    }

    private Tile getTile(int x, int y) {
        return isValidPosition(x, y) ? grid[x][y] : null;
    }

    public void setTile(Position position, Tile tile) {
        setTile(position.x(), position.y(), tile);
    }

    private void setTile(int x, int y, Tile tile) {
        if (isValidPosition(x, y)) {
            grid[x][y] = tile;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("C - %d - %d", width, height);
    }
}